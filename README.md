## About
This library simply holds a list of the contributors of our mods and adds the contributor cape to those players if they play with the mod they contributed to.

## Versioning
We don't include the mc version in the maven path, thus the mod version is important.
We start with 1.0.0 for 1.20.1. Whenever we port to a major mc version like 1.20.4 or 1.21.1, we increase the mod id.

## Release Process
1. Make sure you have the latest changes on this branch.
2. Set the correct mod version in the gradle properties.
3. Then run `git tag -a release-<minecraft_version>-<mod_version>`.
4. Write changelog.
5. Run `git push origin release-<minecraft_version>-<mod_version>`.