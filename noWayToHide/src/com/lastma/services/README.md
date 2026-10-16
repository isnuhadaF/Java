# `services/`

Services implement **application use cases**: they define the work the
application performs in response to a request. A service can coordinate
multiple operations, call the model or policy that owns a rule, and use
repositories to obtain or persist information.

## Responsibilities

- Express an application operation in terms meaningful to the application,
  not in terms of an HTTP route or a database query.
- Coordinate steps and enforce application-level rules that span components.
- Define or consume clear data-access contracts, depending on where the
  project places repository abstractions.
- Return an outcome that an inbound adapter can translate for its caller.

## Boundaries and design notes

Keep services independent of transport-specific request and response types
where practical, so the same use case can be called from another adapter.
Storage mechanics such as SQL, file formats, and driver APIs belong behind
repository operations. Whether the service owns a repository interface or
depends on one in `data/repositories` is a design choice: dependency inversion
places the abstraction with the policy that needs it and lets data access
implement it, while a conventional layered arrangement may expose data
operations from the lower layer.

Avoid turning services into unstructured “god” objects or putting every rule
in a controller. Keep each service cohesive around related use cases, and test
observable outcomes rather than its private call sequence unless interaction
itself is part of the contract.
