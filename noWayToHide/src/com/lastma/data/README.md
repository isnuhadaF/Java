# `data/`

This package groups **data-access responsibilities**: the representations
used by persistence and the components that read or write data. It is a
conventional lower layer in this project's teaching layout.

## Contents

- [`models/`](models/README.md): data-layer representations, often shaped for
  storage and mapping.
- [`repositories/`](repositories/README.md): operations that abstract
  retrieval and persistence.

Keep database, file, or remote-service mechanics behind repository
operations. A service may depend on a repository contract, while a repository
implementation translates that contract into a storage operation. Data code
must not call controllers or construct transport responses.

The word “model” is context-dependent. A persistence model is not necessarily
a domain model/entity, and the repository does not automatically own all
business rules. Put invariants with the concept or use case that owns them;
use mapping when persistence representation and application representation
need to evolve independently.
