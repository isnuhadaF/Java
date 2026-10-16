# noWayToHide

This Java project is a teaching scaffold for **layered architecture**. The
folders show one way to separate an application by responsibility: handling
requests, coordinating use cases, and accessing data. The package names
describe intended roles; they do not imply that every layer or component has
already been implemented.

Layered architecture is a family of designs rather than one universal folder
layout. In the conventional three-layer arrangement used here, an inbound
controller calls an application service, which uses a repository boundary to
work with data. The data layer should not call the controller. A controller
should not bypass the service to run storage operations, because that couples
the external interface to persistence and makes behavior harder to test.

## Typical request path

1. A caller reaches a controller through an entry point (for example, an HTTP
   route, if the project later adds an HTTP framework).
2. The controller translates transport input into an application-level
   request, performs boundary checks, and calls the appropriate service.
3. The service executes the use case, coordinates required work, and applies
   application rules. Rules that must always hold should be enforced by the
   model that owns them or by the service responsible for the operation, not
   only by a user interface or controller.
4. The service uses a repository contract to retrieve or persist data. A
   repository implementation maps between that contract and a particular
   storage mechanism.
5. The service returns a result. The controller translates it into the
   appropriate external response, often represented by a response DTO.

This is a conceptual path, not a requirement that every request use every
folder. A small operation may need no repository; an application may have
additional layers or adapters as it grows.

## Dependency and boundary notes

In a conventional layered design, higher-level code uses the services offered
by the layer below it: controllers use services, and services use data-access
operations. Keep those dependencies explicit and one-way; lower layers should
not reach upward to invoke controllers or format transport responses. A
repository interface is an abstraction boundary, but its package ownership
depends on the chosen design. If the goal is dependency inversion, the
application layer can own the interface while the data layer supplies its
implementation. Do not confuse this optional refinement with the basic
layering pattern.

DTOs, application models, and persistence models are different concepts even
when their fields happen to look alike. DTOs carry data across a boundary;
data models in `data/models` describe representations used by data access.
Neither name alone determines whether an object is a domain entity. Avoid
coupling an external API contract to database representation, and map between
representations when that separation is useful.

## Package guide

- [`src/`](src/README.md): production Java source root.
- [`src/com/`](src/com/README.md) and
  [`src/com/lastma/`](src/com/lastma/README.md): Java namespace and
  application package root.
- [`controllers/`](src/com/lastma/controllers/README.md): inbound boundary.
- [`services/`](src/com/lastma/services/README.md): application use cases.
- [`data/`](src/com/lastma/data/README.md): data-access responsibilities,
  including [`models/`](src/com/lastma/data/models/README.md) and
  [`repositories/`](src/com/lastma/data/repositories/README.md).
- [`dtos/`](src/com/lastma/dtos/README.md): boundary data contracts, with
  [`requests/`](src/com/lastma/dtos/requests/README.md) and
  [`responses/`](src/com/lastma/dtos/responses/README.md).
- [`exceptions/`](src/com/lastma/exceptions/README.md): application-specific
  error types.
- [`utils/`](src/com/lastma/utils/README.md): narrowly scoped shared helpers.
- [`test/`](test/README.md): automated tests.
- [`.idea/`](.idea/README.md): IntelliJ IDEA project settings; not an
  application layer.

The source and test roots are configured in `noWayToHide.iml`. Follow the
package responsibility rather than choosing a folder merely because it is
convenient, and write tests for behavior at the level where that behavior is
owned.
# noWayToHide
