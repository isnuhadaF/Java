# `dtos/requests/`

Request DTOs describe the data accepted at an inbound boundary for an
operation. They provide a typed representation of caller input and make the
expected input shape visible to maintainers and tests.

## Use and validation

The controller or adapter typically parses transport data into a request DTO,
checks boundary-level requirements, and passes it to a service. A request
object may also carry declarative validation metadata if the project adopts
such a mechanism. Parsing and structural validation do not replace checks of
business invariants: those must be enforced where the rule is owned so they
cannot be bypassed by another caller.

Keep request DTOs focused on accepted input. They should not retrieve data,
perform storage operations, or implement the use case. Avoid accepting
internal persistence fields merely because they are convenient to populate.
