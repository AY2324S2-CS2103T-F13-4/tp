---
layout: page
title: John Doe's Project Portfolio Page
---

### Project: ClassMonitor

ClassMonitor is a **desktop app for managing student performances, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ClassMonitor can get your contact management tasks done faster than traditional GUI apps.

The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to give stars to students for class participation.
  * What it does: allows the user to give students a defined number of stars for class participation. Users can also edit the number of stars directly using the `edit` method.
  * Justification: This feature improves the product significantly because a user (teaching assistant) can now track the performance of students in class, using the number of stars as a gauge for a student's engagement in class.
  * Highlights: This enhancement introduces a new field under student which diverges from the design of other fields like `name` or `phone number`, which are replaced upon edit, however, we allow for replacement of value via `edit`, as well as accumulation of stars using `star`. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required proper exception handling to isolate the changes made to the number of stars by `star` and `edit` commands.
  * Credits: *There is partial use of AB3 code. I was compelled to use the AB3 code design to ensure coherence of code for my parser and command classes.*

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s2.github.io/tp-dashboard/?search=f13-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=c-wenlong&tabRepo=AY2324S2-CS2103T-F13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed milestones `v1.3` and `v1.4` (2 milestone) on GitHub.
  * Encouraged and helped other team members manage their milestones on Github.

* **Enhancements to existing features**:
  * Create white UI Theme of ClassMonitor (Pull requests [\#60](https://github.com/AY2324S2-CS2103T-F13-4/tp/pull/60))

* **Documentation**:
  * User Guide:
    * Update User Guide to include Star Command [\#33](https://github.com/AY2324S2-CS2103T-F13-4/tp/pull/33)
    * Update User Guide from ped comments: [\#145](https://github.com/AY2324S2-CS2103T-F13-4/tp/pull/145)
  * Developer Guide:
    * Update Developer Guide to include Star Command (Pull requests [\#56](https://github.com/AY2324S2-CS2103T-F13-4/tp/pull/56), [\#52](https://github.com/AY2324S2-CS2103T-F13-4/tp/pull/52))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#28](https://github.com/AY2324S2-CS2103T-F13-4/tp/pull/26), [\#35](https://github.com/AY2324S2-CS2103T-F13-4/tp/pull/28), [\#49](https://github.com/AY2324S2-CS2103T-F13-4/tp/pull/33), [\#59](https://github.com/AY2324S2-CS2103T-F13-4/tp/pull/35)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2324S2/forum/issues/10), [2](https://github.com/nus-cs2103-AY2324S2/forum/issues/228), [3](https://github.com/nus-cs2103-AY2324S2/forum/issues/365), [4](https://github.com/nus-cs2103-AY2324S2/forum/issues/424))
  * Reported 21 bugs and suggestions for other teams in the class during the ped
  * Tested and provided suggestions for classmates' ip projects

