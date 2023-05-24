rootProject.name = "RuFi-core-wrapper"
val destination = File(".git/hooks/commit-msg")

plugins {
  id("org.danilopianini.gradle-pre-commit-git-hooks") version "1.1.7"
}

gitHooks {
  commitMsg {
    conventionalCommits()
  }
  createHooks(true)
}
