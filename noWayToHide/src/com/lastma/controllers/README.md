# `controllers/`

Controllers are **inbound adapters**: they connect an external caller to an
application operation. The caller might be an HTTP client, command-line
interface, or another entry point; this folder alone does not imply that a
particular transport framework is present.

## Responsibilities

- Accept transport-level input and perform translation into an application
  request or method arguments.
- Apply boundary checks such as required fields, parseability, and
  authentication/authorization integration when that concern is part of the
  entry point.
- Invoke a service for the requested use case.
- Translate the service outcome into a transport-level result, including an
  appropriate response DTO or error representation where relevant.

## Keep the boundary thin

Controllers should not run persistence queries or orchestrate a use case
across repositories. Those choices couple delivery details to storage and
scatter application policy across entry points. A check that protects a
business invariant must not live only in a controller: other callers could
bypass it, so enforce that rule in the owning model or service as well.

Controllers are allowed to contain boundary-specific translation and
validation; “thin” means delegating application behavior, not having no
responsibility. Their outcomes should be testable without requiring tests to
duplicate the internals of the service layer.
