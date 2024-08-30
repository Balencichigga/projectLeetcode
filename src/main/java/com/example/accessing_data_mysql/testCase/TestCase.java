package com.example.accessing_data_mysql.testCase;

import java.util.ArrayList;
import java.util.List;

public class TestCase<T,K> {
    private Integer problemID;
    private T[] input;
    private K expectedOutput;

    public TestCase(int problemId, T[] input, K expectedOutput) {
        this.problemID = problemId;
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    public int getProblemID() {
        return problemID;
    }
    public T[] getInput() {
        return input;
    }
    public K getExpectedOutput() {
        return expectedOutput;
    }
}

// it's possible to have many testcases per question
/*class TestCaseList<T,K> {
    private List<TestCase<T,K>> testCasesList;

    public TestCaseList() {
        this.testCasesList = new ArrayList<>();
    }
    public void addTestCase(TestCase<T,K> testCase) {
        this.testCasesList.add(testCase);
    }
    public List<TestCase<T,K>> getTestCases() {
        return testCasesList;
    }
    public List<TestCase<T,K>> getTestCasesByProblemId(int problemID) {
        List<TestCase<T,K>> result = new ArrayList<>();
        for (TestCase<T,K> testCase : testCasesList) {
            if (testCase.getProblemID() == problemID) {
                result.add(testCase);
            }
        }
        return result;
    }
}*/
