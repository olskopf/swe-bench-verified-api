# SWE-Bench-Lite API - Task Service

A lightweight REST API for serving real-world bug-fixing tasks derived from the [SWE-bench Lite](https://huggingface.co/datasets/princeton-nlp/SWE-bench_Lite) benchmark dataset.  
This service is designed for integration with autonomous agent systems (e.g., CrewAI, Autogen, LangChain) that require realistic prompts including repository metadata, commit references, and patch diffs.

---

## 📦 Dataset Origin

The source dataset is [SWE-bench Lite](https://huggingface.co/datasets/princeton-nlp/SWE-bench_Lite), provided by Princeton NLP.

> Format: Apache Parquet (converted to JSON)

---

## 🌐 REST API Overview

### `GET /task/index/{index}`  
Returns a task using its artificial numeric index (1-based).

---

# 🧾 JSON Structure of `GET /task/index/{index}`

The `/task/{index}` endpoint returns a single SWE-bench task with full metadata from the original benchmark plus helper fields. Here's a typical structure:

```json
{
  "taskNumber": 1,
  "instance_id": "astropy__astropy-12907",
  "repo": "astropy/astropy",
  "commit": null,
  "Base_commit": "d16bfe05a744909de4b27f5875fe0d4ed41ce607",
  "issue_title": "Fix NullPointerException in handler",
  "issue_body": "Detailed issue description from GitHub...",
  "patch": "diff --git a/... b/... +++ ...",
  "Created_at": "2022-03-03T15:14:54Z",
  "example_index": 0,
  "difficulty": "hard",
  "Environment_setup_commit": "298ccb478e6bf092953bca67a3d29dc6c35f6752",
  "FAIL_TO_PASS": [
    "astropy/modeling/tests/test_separable.py::test_model1",
    "astropy/modeling/tests/test_separable.py::test_model2"
  ],
  "PASS_TO_PASS": [
    "astropy/modeling/tests/test_x",
    "astropy/modeling/tests/test_y"
  ],
  "Hints_text": "Optional hints provided with the task",
  "Problem_statement": "Explanation of the bug or incorrect behavior",
  "Test_patch": "Optional diff for test changes (if available)",
  "Version": "4.3",
  "git_clone": "git clone https://github.com/astropy/astropy.git && cd astropy && git checkout d16bfe05a744909de4b27f5875fe0d4ed41ce607"
}
```

> Notes:
> - `taskNumber`: internal artificial ID (starts at 1)
> - `git_clone`: helper string to reproduce the code state locally
> - All field names mirror those found in SWE-bench Lite where applicable

---

## 💡 Required Fields for Agent Prompting

Agents interacting with this API require a minimal subset of fields to understand and solve the given task. The following fields are considered **essential**:

- `instance_id`: the swe bench identifier for this testcase, important for evaluation with hardness.
- `Problem_statement`: the core bug description or natural language prompt.
- `git_clone`: a convenience string for checking out the correct state.
- `FAIL_TO_PASS`: list of tests the patch is expected to fix.
- `PASS_TO_PASS`: sanity check tests that should remain passing.

An agent must be able to understand the problem and have enough information to reproduce the environment and test the patch impact.

---

## 🐳 Run from DockerHub

You can run this service directly from DockerHub without building it:

```bash
docker pull paulroewer/swe-bench-lite-api:latest
docker run -p 8081:8080 paulroewer/swe-bench-lite-api
```

---

## 📜 License

The underlying SWE-bench dataset is licensed under the terms provided by [HuggingFace](https://huggingface.co/datasets/princeton-nlp/SWE-bench_Lite).  
This project is intended for research and development purposes only.