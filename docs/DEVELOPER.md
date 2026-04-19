# Developer Guide

Welcome to the Aughtone-Format project. This guide provides information on how to contribute to this repository.

## 🤖 AI Collaboration

This project leverages AI agents for development. To maintain architectural integrity and ensure alignment with project goals, all AI agents must follow a **Plan-First** workflow.

### The Plan-First Workflow
1.  **Analyze**: The agent analyzes the task and the existing codebase/documentation.
2.  **Propose**: The agent presents a detailed implementation plan.
3.  **Approve**: The agent **MUST WAIT** for explicit approval from a human developer before proceeding with any file modifications.
4.  **Execute**: Once approved, the agent performs the changes as described in the plan.
5.  **Verify**: The agent verifies the changes (e.g., by running builds or tests).

## 🚀 Deployment

### Production Deployment
Production releases are handled automatically via **GitHub Actions**.
- **Workflow**: `.github/workflows/publish.yml`
- **Trigger**: Pushing to the `main` branch.
- **Destination**: Maven Central.
- **Requirements**: Repository secrets for Maven Central credentials and GPG signing keys.

### Local Deployment
You can publish the library to your local Maven repository for testing:
1. Run the local publish script:
   ```bash
   ./publish-local.sh
   ```
   This script executes `./gradlew -Pskip-signing=true publishToMavenLocal`, which bypasses GPG signing for local use.

## 🛠️ Environment Setup
(Add environment setup instructions here)

## 🏗️ Project Structure
(Add project structure overview here)
