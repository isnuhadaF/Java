# `test/`

This is the test source root configured in `noWayToHide.iml`. It contains
automated tests, not production classes. Organize test packages to make their
relationship to the production code clear; tests may mirror the
`com.lastma` package structure.

## Test at the level of responsibility

- Test services through use-case inputs and observable outcomes, including
  important application rules.
- Test controllers for boundary translation and response behavior without
  duplicating every service rule.
- Test repository behavior for data mapping and storage semantics. Use a
  suitable test database, controlled fake, or other test mechanism according
  to the actual implementation.
- Use focused unit tests for isolated logic and integration tests when
  behavior depends on collaboration between components or infrastructure.

Tests are executable specifications, but they should normally assert
observable contracts rather than private implementation details. Avoid tests
that pass only because of a particular internal call sequence unless that
interaction is itself required. Keep test setup representative and isolated
so one test's state does not make another test pass or fail.

No particular testing framework is implied by this folder. Follow the
dependencies and conventions established when a test framework is added.
