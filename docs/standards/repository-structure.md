# Repository Structure

Where things live, and what to read before changing them. [docs/README.md](../README.md) explains the documentation layout in full.

## Where things live

1. **Work** — stories, bugs and their acceptance criteria — is on the GitHub issue tracker, never in a file.
2. **Knowledge** is in `docs/knowledge/`: decisions (`decisions/`), specifications (`specifications/`), developer guides (`guides/`) and research (`research/`).
3. **Machinery** — `AGENTS.md`, `docs/README.md` and this folder — is plain git.
4. **Artifacts for outside readers** are in `docs/outbox/`; retired files are in `docs/_archive/`.

## Context rules

1. Before writing logic, read the story's acceptance criteria on its issue, and the specifications for the module.
2. Before changing how the library is built or how a module is organised, read the architecture specification and the decision records.
3. When a change alters documented behaviour, update the document that describes it in the same change.
4. File a new document in the section whose `README.md` describes it.
