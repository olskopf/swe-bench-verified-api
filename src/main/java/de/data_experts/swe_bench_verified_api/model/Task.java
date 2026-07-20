package de.data_experts.swe_bench_verified_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {

    @JsonIgnore
    private int taskNumber;

    @JsonProperty("instance_id")
    private String instanceId;

    @JsonProperty("repo")
    private String repo;

    @JsonProperty("base_commit")
    private String baseCommit;

    @JsonProperty("patch")
    private String patch;

    @JsonProperty("test_patch")
    private String testPatch;

    @JsonProperty("problem_statement")
    private String problemStatement;

    @JsonProperty("hints_text")
    private String hintsText;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("version")
    private String version;

    @JsonProperty("FAIL_TO_PASS")
    private String failToPass;

    @JsonProperty("PASS_TO_PASS")
    private String passToPass;

    @JsonProperty("environment_setup_commit")
    private String environmentSetupCommit;

    @JsonProperty("difficulty")
    private String difficulty;

    @JsonProperty("git_clone")
    public String getGitCloneCommand() {
        if (repo != null && baseCommit != null) {
            String repoUrl = "https://github.com/" + repo + ".git";
            String repoName = repo.contains("/") ? repo.split("/")[1] : repo;
            return "git clone " + repoUrl + " && cd " + repoName + " && git checkout " + baseCommit;
        }
        return null;
    }

    @JsonProperty("taskNumber")
    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getInstanceId() { return instanceId; }
    public void setInstanceId(String instanceId) { this.instanceId = instanceId; }

    public String getRepo() { return repo; }
    public void setRepo(String repo) { this.repo = repo; }

    public String getBaseCommit() { return baseCommit; }
    public void setBaseCommit(String baseCommit) { this.baseCommit = baseCommit; }

    public String getPatch() { return patch; }
    public void setPatch(String patch) { this.patch = patch; }

    public String getTestPatch() { return testPatch; }
    public void setTestPatch(String testPatch) { this.testPatch = testPatch; }

    public String getProblemStatement() { return problemStatement; }
    public void setProblemStatement(String problemStatement) { this.problemStatement = problemStatement; }

    public String getHintsText() { return hintsText; }
    public void setHintsText(String hintsText) { this.hintsText = hintsText; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public String getFailToPass() { return failToPass; }
    public void setFailToPass(String failToPass) { this.failToPass = failToPass; }

    public String getPassToPass() { return passToPass; }
    public void setPassToPass(String passToPass) { this.passToPass = passToPass; }

    public String getEnvironmentSetupCommit() { return environmentSetupCommit; }
    public void setEnvironmentSetupCommit(String environmentSetupCommit) { this.environmentSetupCommit = environmentSetupCommit; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
}
