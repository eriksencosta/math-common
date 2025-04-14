# Releasing a new version

* [ ] Make sure the build passed successfully
* [ ] Update the `CHANGELOG.md` file
* [ ] Update the `README.md` file (at least the version in the Installation section)
* [ ] Create the release commit (`git commit -S`)
* [ ] Create the tag (`git tag -a -s -m "Tag release vx.y.z"`)
* [ ] Push the commit and the tag
* [ ] Bump the version in the `gradle.properties` file
