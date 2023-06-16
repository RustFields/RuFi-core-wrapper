const config = require('semantic-release-preconfigured-conventional-commits')
const prepareCommands = `
sed -i 's/version := .*/version := "\${nextRelease.version}"/g' build.sbt || exit 1
sed -i 's/version.*/version = "\${nextRelease.version}"/g' native/Cargo.toml || exit 2
git add -A || exit 3
git commit -m "chore: [skip ci] update version in build.sbt and in Cargo.toml" || exit 4
git push --force origin || exit 5`

const publishCommands = `
git tag -a -f \${nextRelease.version} \${nextRelease.version} -F CHANGELOG.md  || exit 1
git push --force origin \${nextRelease.version} || exit 2
sbt cargoBuild || exit 3
sbt publishLocal || exit 4
`
const releaseBranches = ["main"]
config.branches = releaseBranches
config.plugins.push(
    ["@semantic-release/exec", {
        "prepareCmd": prepareCommands,
        "publishCmd": publishCommands,
    }],
    ["@semantic-release/github", {
        "assets": [
            { "path": "core/native/target/scala*/*.jar" },
            { "path": "native/target/release/*.so"},
        ]
    }],
    ["@semantic-release/git", {
        "assets": ["CHANGELOG.md", "package.json"],
        "message": "chore(release)!: [skip ci] ${nextRelease.version} released"
    }],
)
module.exports = config