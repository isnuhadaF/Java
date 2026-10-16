# `com.lastma`

This is the project package root under the Java source root. Its subpackages
group production classes by architectural responsibility. A package is a
code-organization and visibility mechanism; it is not, by itself, proof that
the application has achieved separation of concerns. The classes must also
respect the boundaries described below.

## Intended organization

- [`controllers/`](controllers/README.md) adapts inbound calls to application
  operations.
- [`services/`](services/README.md) implements and coordinates application
  use cases.
- [`data/`](data/README.md) groups data representations and persistence
  operations.
- [`dtos/`](dtos/README.md) groups request and response data contracts.
- [`exceptions/`](exceptions/README.md) defines meaningful application error
  types.
- [`utils/`](utils/README.md) holds small cross-cutting helpers that do not
  belong to a particular layer.

When deciding where a class belongs, ask which responsibility changes would
cause it to change. Transport-specific changes usually belong at the
controller boundary; use-case policy belongs in services or the model that
owns the rule; storage technology changes belong behind repository
operations. Keep dependencies explicit and avoid calls from data access back
into controllers.
