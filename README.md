# SWE-Bench-Verified API - Task Service
A lightweight REST API for serving real-world bug-fixing tasks derived from the [SWE-bench Verified](https://huggingface.co/datasets/princeton-nlp/SWE-bench_Verified) benchmark dataset.  
This service is designed for integration with autonomous agent systems that require realistic prompts including repository metadata, commit references, and patch diffs.

Fork of [PaulRoewer/swe-bench-lite-api](https://github.com/PaulRoewer/swe-bench-lite-api), adapted for SWE-bench Verified.

---
## 📦 Dataset Origin
The source dataset is [SWE-bench Verified](https://huggingface.co/datasets/princeton-nlp/SWE-bench_Verified), a manually validated subset of ~500 issues curated by OpenAI.  
Unlike SWE-bench Lite, all problem statements and FAIL_TO_PASS tests have been reviewed by human annotators.

> Format: Apache Parquet (converted to JSON)

---
## 🌐 REST API Overview
### `GET /task/index/{index}`  
Returns a task using its artificial numeric index (1-based).

---
# 🧾 JSON Structure of `GET /task/index/{index}`
```json
{
  "taskNumber": 1,
  "instance_id": "astropy__astropy-12907",
  "repo": "astropy/astropy",
  "Base_commit": "d16bfe05a744909de4b27f5875fe0d4ed41ce607",
  "issue_title": "Fix NullPointerException in handler",
  "issue_body": "Detailed issue description from GitHub...",
  "patch": "diff --git a/... b/...",
  "Created_at": "2022-03-03T15:14:54Z",
  "difficulty": "<15 min fix",
  "FAIL_TO_PASS": ["..."],
  "PASS_TO_PASS": ["..."],
  "Problem_statement": "Explanation of the bug or incorrect behavior",
  "git_clone": "git clone https://github.com/astropy/astropy.git && cd astropy && git checkout d16bfe05a744909de4b27f5875fe0d4ed41ce607"
}
```

> Notes:
> - `difficulty`: Verified-specific field (`<15 min fix`, `15 min - 1 hour`, `>1 hour`)
> - `taskNumber`: internal artificial ID (starts at 1)
> - `git_clone`: helper string to reproduce the code state locally

---
## 💡 Required Fields for Agent Prompting
- `instance_id`: SWE-bench identifier for evaluation.
- `Problem_statement`: the core bug description.
- `git_clone`: convenience string for checking out the correct state.
- `FAIL_TO_PASS`: tests the patch is expected to fix.
- `PASS_TO_PASS`: sanity check tests that should remain passing.
- `difficulty`: optional filter for experiment design.

---
## 🐳 Run from DockerHub
```bash
docker pull olskopf/swe-bench-verified-api:latest
docker run -p 8081:8080 olskopf/swe-bench-verified-api
```

---
## 📜 License
The underlying SWE-bench dataset is licensed under the terms provided by [HuggingFace](https://huggingface.co/datasets/princeton-nlp/SWE-bench_Verified).  
This project is intended for research and development purposes only.
