package com.example.accessing_data_mysql;

import com.example.accessing_data_mysql.testCase.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import java.util.Arrays;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

@RestController
@RequestMapping(path ="/problems")
public class ProblemController {
    @Autowired
    private ProblemRepository problemRepository;


    @PostMapping(path = "/add")
    public String addProblem(@RequestBody Problem problem) {
        problemRepository.save(problem);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    @PutMapping(path = "/{id}")
    public Problem updateProblem(@RequestBody Problem Newproblem, @PathVariable Integer id) {
        Optional<Problem> existProblem = problemRepository.findById(id);
        Problem problem = existProblem.get();
        problem.setProblemName(Newproblem.getProblemName());
        problem.setDescription(Newproblem.getDescription());
        return problemRepository.save(problem);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable Integer id) {
        problemRepository.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public Problem getProblemById(@PathVariable Integer id) {
        return problemRepository.findById(id).get();
    }
    /*
    @PutMapping(path = "/{id}/submitCode")
    public String updateProblemCode(@PathVariable Integer id, @RequestBody CodeRequest codeRequest) throws IOException {
        // create a file
        String code = codeRequest.getCode();
        String className = "UserSolution";
        File javaFile = new File(className + ".java");
        //write into a file
        if (javaFile.createNewFile()) {
            System.out.println("File created: " + javaFile.getName());
            FileWriter writer = new FileWriter(javaFile);
            writer.write(code);
            writer.close();
        } else {
            System.out.println("File already exists.");
            FileWriter writer = new FileWriter(javaFile);
            writer.write(code);
            writer.close();
        }

        //compile
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(javaFile);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
        boolean success = task.call();
        fileManager.close();


        // testcase
        List<TestCase> testCaseList = new ArrayList<>();
        testCaseList.add(new TestCase<>(1, new Integer[]{1, 2}, 3));
        testCaseList.add(new TestCase<>(1, new Integer[]{1, 3}, 4));
        testCaseList.add(new TestCase<>(1, new Integer[]{1, 4}, 5));

        int passCount = 0;
        for (TestCase testCase : testCaseList) {
            //运行UserSolution
            Process process = Runtime.getRuntime().exec("java " + className);
            //带入testcase input去跑
            process.getOutputStream().write(Arrays.toString(testCase.getInput()).getBytes());
            process.getOutputStream().flush();
            //get output
            String output = new String(process.getInputStream().readAllBytes());
            //output跟expected Output比
            if (output.equals(testCase.getExpectedOutput().toString())) {
                passCount++;
            }
        }
        // return result
        return String.format("%d/%d pass", passCount, testCaseList.size());

    }*/
    @PutMapping(path = "/{id}/submitCode")
    public String updateProblemCode(@PathVariable Integer id, @RequestBody CodeRequest codeRequest) throws IOException, InterruptedException {
        // Get the user's submitted code
        String code = codeRequest.getCode();
        String className = "UserSolution";
        File javaFile = new File(className + ".java");

        // Write the user's code to a file
        try (FileWriter writer = new FileWriter(javaFile)) {
            writer.write(code);
        }

        // Compile the Java file
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(javaFile);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
        boolean success = task.call();
        fileManager.close();

        if (!success) {
            return "Compilation failed: " + diagnostics.getDiagnostics().toString();
        }

        // test cases
        List<TestCase<Integer, Integer>> testCaseList = new ArrayList<>();
        testCaseList.add(new TestCase<>(1, new Integer[]{1, 2}, 3));
        testCaseList.add(new TestCase<>(1, new Integer[]{1, 3}, 4));
        testCaseList.add(new TestCase<>(1, new Integer[]{1, 4}, 5));

        int passCount = 0;

        //遍历 testCaseList
        for (TestCase<Integer, Integer> testCase : testCaseList) {
            // use ProcessBuilder to run  Java program
            ProcessBuilder builder = new ProcessBuilder("java", className);
            builder.redirectErrorStream(true);  // Merge error stream with standard output
            Process process = builder.start();

            // Write the test case input to the user program input
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            for (Integer input : testCase.getInput()) {
                writer.write(input.toString());
                writer.newLine();  //newline
            }
            writer.flush();
            writer.close();  // Close the writer manually

            // get the program's output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            reader.close();

            // wait for the process to end
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                return "Execution failed with exit code: " + exitCode;
            }

            // compare the actual output with the expected output
            System.out.println("Expected Output: " + testCase.getExpectedOutput().toString());
            System.out.println("Actual Output: " + output.toString().trim());
            if (output.toString().trim().equals(testCase.getExpectedOutput().toString().trim())) {
                passCount++;
            } else {
                System.out.println("Test case failed for input: " + Arrays.toString(testCase.getInput()));
            }
        }

        // return the result
        return String.format("%d/%d pass", passCount, testCaseList.size());


    }

}
class CodeRequest {
    Integer userId;
    String code;
    public CodeRequest(Integer userId, String code) {
        this.userId = userId;
        this.code = code;
    }
    public Integer getUserId() {
        return userId;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}