---
layout: page
title: Developer Guide
show-toc: true
---

## About ClassMonitor

ClassMonitor is a **Student Management System** that empowers TAs and Professors to manage their student particulars and obtain insights about their students' performance ratings. As a TA, you can easily view and edit your students' particulars during your daily classes. Utilize ClassMonitor’s flexible **tagging system** to help you organize your students according to their modules and classes. Finally, streamline your performance grading decisions by deriving insights from your students' performance indicators across time, through ClassMonitor’s **statistics** – you can allocate stars to students for good class participation and bolts for absenteeism!

The Developer Guide offers comprehensive documentation on the design and implementation of ClassMonitor. It includes details on the architecture of ClassMonitor, specifications for individual components, and an overview of the software's functionality and operations.

{% include toc.md header=true show-in-toc=true ordered=true %}
--------------------------------------------------------------------------------------------------------------------

## Acknowledgements
ClassMonitor is a brownfield software project based off [AddressBook Level-3](https://se-education.org/addressbook-level3/), taken under the [CS2103T Software Engineering module](https://nus-cs2103-ay2324s1.github.io/website/index.html) held by the School of Computing at the National University of Singapore.

Java dependencies:
* [Jackson](https://github.com/FasterXML/jackson) for JSON-related operations
* [JavaFX](https://openjfx.io/) for GUI
* [JUnit5](https://github.com/junit-team/junit5) for testing

Documentation dependencies:
* [Jekyll](https://jekyllrb.com/) for rendering the website
* [PlantUML](https://plantuml.com/) for creating UML diagrams

Fonts used in ClassMonitor:
* ClassMonitor Website:
    * [Epilogue](https://fonts.google.com/specimen/Epilogue) by Tyler Finck
    * [Source Code Pro](https://fonts.google.com/specimen/Source+Code+Pro) by Paul D. Hunt

Other acknowledgments:
* Material Design Icons:
    * [star](https://www.canva.com/p/sketchify/?utm_medium=referral&utm_source=creator_share&utm_campaign=creator_share) by Sketchify on Canva as ClassMonitor's App Icon
* Font Awesome 6 icons:
    * [book](https://fontawesome.com/icons/book) as the "note" admonition icon
    * [circle-info](https://fontawesome.com/icons/circle-info) as the "info" admonition icon
    * [fire-flame-curved](https://fontawesome.com/icons/fire-flame-curved) as the "danger" admonition icon
    * [lightbulb](https://fontawesome.com/icons/lightbulb) as the "tip" admonition icon
    * [triangle-exclamation](https://fontawesome.com/icons/triangle-exclamation) as the "warning" admonition icon
* Code acknowledgements:
    * [Website Layout](https://github.com/AY2324S2-CS2103T-F13-4/tp/tree/master/docs) adapted from [AY2223S1-CS2103T-W16-2's tP Project](https://github.com/AY2223S1-CS2103T-W16-2/tp/tree/master/docs)
    * [Custom link fragment checker](https://github.com/AY2223S1-CS2103T-W16-2/tp/blob/master/cli-test/linkchecker/check-links.js) adapted from the [World Wide Web Consortium](https://github.com/w3c/node-linkchecker)
    * [Pure CSS auto-incrementing heading counters](https://github.com/AY2223S1-CS2103T-W16-2/tp/blob/master/docs/_sass/toc.scss) adapted from [@gvgramazio on StackOverflow](https://stackoverflow.com/a/51007932/9311854)
    * [PDF styling of HTML pages](https://github.com/AY2223S1-CS2103T-W16-2/tp/blob/master/docs/_sass/pdf.scss) following Michael Perrin's [tutorial](https://www.michaelperrin.fr/blog/2019/11/printing-the-web-part-2-html-and-css-for-printing-books)
    * [Pure liquid admonitions](https://github.com/AY2223S1-CS2103T-W16-2/tp/blob/master/docs/_sass/admonitions.scss) adapted from [@kimkyunghwan21 on GitHub](https://github.com/kimkyunghwan21/kimkyunghwan21.github.io/blob/master/_layouts/plugins/extension.liquid)
    * [Pure liquid HTML table-of-contents](https://github.com/AY2223S1-CS2103T-W16-2/tp/blob/master/docs/_includes/toc.html) from [@allejo on GitHub](https://github.com/allejo/jekyll-toc)
* Miscellaneous:
    * [Docusaurus](https://docusaurus.io/docs/markdown-features/admonitions) for admonition colors and styling
    * [SchemeColor](https://www.schemecolor.com/pastel-rainbow.php) for admonition colors


## Setting up, getting started

Refer to the guide [_Setting up and getting started_](SettingUp.md).


## Design

```tip
The `.puml` files used to create the diagrams in this document are in the `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
```

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of the main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S2-CS2103T-F13-4/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S2-CS2103T-F13-4/tp/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point).

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside components from being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S2-CS2103T-F13-4/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S2-CS2103T-F13-4/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` objects residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S2-CS2103T-F13-4/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("find name Alex")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/FindStarsLessThanSequenceDiagram.png)

```note
The lifeline for `FindCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
```

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to `ClassMonitorParser` object which in turn creates a parser that matches the command (e.g., `FindCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `FindCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to return students with names that match the specified keyword).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `ClassMonitorParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `ClassMonitorParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S2-CS2103T-F13-4/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores ClassMonitor data i.e., all `Student` objects (which are contained in a `UniqueStudentList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

```note
An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `ClassMonitor`, which `Student` references. This allows `ClassMonitor` to only require one `Tag` object per unique tag, instead of each `Student` needing their own `Tag` objects.<br>


<img src="images/BetterModelClassDiagram.png" width="450" />
```


### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S2-CS2103T-F13-4/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both ClassMonitor data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `ClassMonitorStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## Implementation

This section describes some noteworthy details on how certain features are implemented.

### Awarding Stars to a Student

#### Overview

The star mechanism is facilitated by `StarCommand`, which is called by its `execute` method to add stars to a `Student`.

* `StarCommandParser#parse()` — Parses the parameters of the star command from its command-line String input.
* `StarCommand#execute()` — Updates the `ClassMonitor` with the added stars.

#### Feature Details

Here is the activity diagram showing the process of the `star` command:

![StarActivityDiagram](images/StarActivityDiagram.png)

Here is the sequence diagram showing how a star operation goes through the `Logic`, `Model` and `Storage` components.

![StarSequenceDiagram](images/StarCommandSequenceDiagram.png)

Step 1. The user enters in command: `star 1 s/2`.

Step 2. The `LogicManager` calls on `ClassMonitorParser` to parse the String.

Step 3. The `ClassMonitorParser` calls `StarCommandParser.parse()`, which returns a `StarCommand`.

```note
If the number of stars is non-positive (i.e. < 1), then it will raise a parse exception.
```


Step 4. `LogicManager` calls on `StarCommand.execute()`, which updates the ClassMonitor with the new number of stars.

#### Design considerations:

**Aspect: How star executes:**

* **Method 1:** Increments the number of stars using `Star` command.
    * Pros: Easy to implement, easy to use.
    * Cons: Does not allow user to edit the number of stars.

* **Method 2:** Updates the number of stars using `Edit` command.
    * Pros: Able to edit the number of stars however one desires.
    * Cons: Command is not modularised, user have to calculate the number of stars themselves when updating.

    
### Awarding Bolts to a Student

#### Overview

The bolt mechanism is facilitated by `BoltCommand`, which is called by its `execute` method to add bolts to a `Student`.

* `BoltCommandParser#parse()` — Parses the parameters of the bolt command from its command-line String input.
* `BoltCommand#execute()` — Updates the `ClassMonitor` with the added bolts.

#### Feature Details

Here is the activity diagram showing the process of the `bolt` command:

![BoltActivityDiagram](images/BoltActivityDiagram.png)

Here is the sequence diagram showing how a bolt operation goes through the `Logic`, `Model` and `Storage` components.

![BoltSequenceDiagram](images/BoltCommandSequenceDiagram.png)

Step 1. The user enters in command: `bolt 1 b/2`.

Step 2. The `LogicManager` calls on `ClassMonitorParser` to parse the String.

Step 3. The `ClassMonitorParser` calls `BoltCommandParser.parse()`, which returns a `BoltCommand`.

```note
If the number of bolts is non-positive (i.e. < 1), then it will raise a parse exception.
```

Step 4. `LogicManager` calls on `BoltCommand.execute()`, which updates the ClassMonitor with the new number of bolts.

#### Design considerations:

**Aspect: How bolt executes:**

* **Method 1:** Increments the number of bolts using `Bolt` command.
    * Pros: Easy to implement, easy to use.
    * Cons: Does not allow user to edit the number of bolts.

* **Method 2:** Updates the number of bolts using `Edit` command.
    * Pros: Able to edit the number of bolts however one desires.
    * Cons: Command is not modularised, user have to calculate the number of bolts themselves when updating.


### Sorting Students

#### Overview

The sorting mechanism is facilitated by `SortCommand`, which is called by its `execute` method to sort the students
based on one of its fields either in ascending or descending order

* `SortCommandParser#parse()` — Parses the parameters of the sort command from its command-line String input.
* `SortCommand#execute()` — Updates the ClassMonitor to display the sorted list.

#### Feature Details

Here is the activity diagram showing the process of the `sort` command:

![SortActivityDiagram](images/SortActivityDiagram.png)

Here is the sequence diagram showing how a sort operation goes through the `Logic`, `Model` and `Storage` components.

![SortSequenceDiagram](images/SortCommandSequenceDiagram.png)

Step 1. The user enters in command: `sort name desc`.

Step 2. The `LogicManager` calls on `ClassMonitorParser` to parse the String.

Step 3. The `ClassMonitorParser` calls `SortCommandParser.parse()`, which returns a `SortCommand`.

```note
If either the field `field` or sorting order `isAscending` is not provided, a parse error will be thrown.
```

Step 4. `LogicManager` calls on `SortCommand.execute()`, which updates the classmonitor with the new sorted list.

### Finding Students

#### Overview

The find mechanism is facilitated by `FindCommand`, which is called by its `execute` method to filter the students
based on one of its fields and a given criteria

* `FindCommandParser#parse()` — Parses the parameters of the find command from its command-line String input.
* `FindCommand#execute()` — Updates the ClassMonitor to display the filtered list.

#### Feature Details

Here is the activity diagram showing the process of the `Find` command:

![FindActivityDiagram](images/FindActivityDiagram.png)

Here is the sequence diagram showing how a find operation goes through the `Logic`, `Model` and `Storage` components.

![FindSequenceDiagram](images/FindCommandSequenceDiagram.png)

Step 1. The user specifies the field and criteria to find students by, and enters in command: `find field criteria` (e.g. find name Alex).

Step 2. The `LogicManager` calls on `ClassMonitorParser` to parse the String.

Step 3. The `ClassMonitorParser` calls `FindCommandParser.parse()`, which then calls the parser for the specified field (e.g. `FindCommandParser.parseFindName()`), which returns a `FindCommand`.

```note
If the field or criteria specified is invalid, a parse error will be raised.
```

Step 4. `LogicManager` calls on `FindCommand.execute()`, which updates the student list with the new filtered list.


### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedClassMonitor`. It extends `ClassMonitor` with an undo/redo history, stored internally as an `classMonitorStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedClassMonitor#commit()` — Saves the current ClassMonitor state in its history.
* `VersionedClassMonitor#undo()` — Restores the previous ClassMonitor state from its history.
* `VersionedClassMonitor#redo()` — Restores a previously undone ClassMonitor state from its history.

These operations are exposed in the `Model` interface as `Model#commitClassMonitor()`, `Model#undoClassMonitor()` and `Model#redoClassMonitor()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedClassMonitor` will be initialized with the initial ClassMonitor state, and the `currentStatePointer` pointing to that single ClassMonitor state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th student in ClassMonitor. The `delete` command calls `Model#commitClassMonitor()`, causing the modified state of ClassMonitor after the `delete 5` command executes to be saved in the `classMonitorStateList`, and the `currentStatePointer` is shifted to the newly inserted ClassMonitor state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new student. The `add` command also calls `Model#commitClassMonitor()`, causing another modified ClassMonitor state to be saved into the `classMonitorStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

```note
The Developer Guide offers comprehensive documentation on the design and implementation of ClassMonitor. It includes details on the architecture of ClassMonitor, specifications for individual components, and an overview of the software's functionality and operation.If a command fails its execution, it will not call `Model#commitClassMonitor()`, so ClassMonitor state will not be saved into the `classMonitorStateList`.
```

Step 4. The user now decides that adding the student was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoClassMonitor()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous ClassMonitor state, and restores ClassMonitor to that state.

![UndoRedoState3](images/UndoRedoState3.png)

```note
If the `currentStatePointer` is at index 0, pointing to the initial ClassMonitor state, then there are no previous ClassMonitor states to restore. The `undo` command uses `Model#canUndoClassMonitor()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the undo.
```

The following sequence diagram shows how an undo operation goes through the `Logic` component:

![UndoSequenceDiagram](images/UndoSequenceDiagram-Logic.png)

```note
The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
```

Similarly, how an undo operation goes through the `Model` component is shown below:

![UndoSequenceDiagram](images/UndoSequenceDiagram-Model.png)

The `redo` command does the opposite — it calls `Model#redoClassMonitor()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores ClassMonitor to that state.

```note
If the `currentStatePointer` is at index `classMonitorStateList.size() - 1`, pointing to the latest ClassMonitor state, then there are no undone ClassMonitor states to restore. The `redo` command uses `Model#canRedoClassMonitor()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.
```

Step 5. The user then decides to execute the command `list`. Commands that do not modify ClassMonitor, such as `list`, will usually not call `Model#commitClassMonitor()`, `Model#undoClassMonitor()` or `Model#redoClassMonitor()`. Thus, the `classMonitorStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitClassMonitor()`. Since the `currentStatePointer` is not pointing at the end of the `classMonitorStateList`, all ClassMonitor states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire ClassMonitor.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the student being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

### \[Proposed\] Student Comments

#### Feature Proposal

This feature is an extension of the 'Stars' feature. When a TA gives a student stars, they can also leave a comment to explain why the Student received them. Each instance of a user giving stars (with optional comments) can be stored by Student.

![Proposed Class diagram](images\StarCommentModelClassDiagram.png).

#### Command Format

Users will be able to add comments to a student by using the `star` command:

Usage: `star INDEX s/STARS [c/COMMENT]`

the `edit` command will also be extended to allow users to edit the comments.

Usage: `edit INDEX ...c/INDEX2 COMMENT...`

The command will edit the comment at the student with index `INDEX` with comment index `INDEX2`.

#### UI Modifications

**Alternative 1**
* Users will be able to view the comments they have left for each student in a separate **window**. The components of the window will track changes to the student comments in the model.

**Alternative 2**
* Users will be able to view the comments they have left for each student in a separate display in the same window.


![Proposed UI](images\StarCommentUiClassDiagram.png).


--------------------------------------------------------------------------------------------------------------------

## Documentation, logging, testing, configuration, dev-ops

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## Appendix: Requirements

### Product scope

**Target user profile**:

* Teaching Assistant (TA) who is responsible for managing classes of students
* Has a need to track class participation across students
* prefers desktop apps on a laptop
* prefers typing in a CLI interface to mouse interactions

**Value proposition**: ClassMonitor empowers TAs to manage their students and obtain insights from students' data.


### User stories

```info
The following legend describes the symbols used in this section:

| Priority | Symbol  |     Meaning      |
|:--------:|:-------:|:----------------:|
|   High   | `* * *` |    Must have     |
|  Medium  |  `* *`  |   Nice to have   |
|   Low    |   `*`   | Unlikely to have |
```


| Priority | As a …​                               | I want to …​                               | So that I can…​                                                                           |
|----------|---------------------------------------|--------------------------------------------|-------------------------------------------------------------------------------------------|
| `* * *`  | TA using the App for the first time   | see usage instructions                     | refer to instructions when I forget how to use the App                                    |
| `* * *`  | TA                                    | add a new student                          |                                                                                           |
| `* * *`  | TA                                    | edit a student's details                   |                                                                                           |
| `* * *`  | TA                                    | delete a student                           | remove entries that I no longer need                                                      |
| `* * *`  | TA                                    | know the majors of my students             | understand their learning needs                                                           |
| `* * *`  | TA                                    | find a student by name                     | locate details of students without having to go through the entire list                   |
| `* * *`  | TA                                    | track a student's participation            | gauge their engagement in class                                                           |
| `* * *`  | TA                                    | track a student's attendance               | mark and record attendance efficiently                                                    |
| `* * *`  | TA                                    | tag the students by their TGs              | remember which class my students are in                                                   |
| `* * *`  | TA                                    | tag the students by their modules          | remember which module my students are in                                                  |
| `* *`    | TA                                    | filter the students by their TGs           | view all the students from a TG                                                           |
| `* *`    | TA                                    | filter students by their modules           | view all the students from a module                                                       |
| `* *`    | TA                                    | exit the program                           |                                                                                           |
| `* *`    | TA                                    | sort students based on participation       | praise those who have taken initiative and remind those who have not to be more proactive |
| `*`      | TA                                    | have a personal description of the student | know more about them                                                                      |
| `* `     | TA                                    | clear all students' details                | remove all entries quickly                                                                |
| `*`      | TA with many students in ClassMonitor | sort students by name                      | locate a student easily                                                                   |


### Use cases

(For all use cases below, the **System** is the `ClassMonitor` and the **Actor** is the `TA`, unless specified otherwise)

#### Use case 1: Delete a student

[[ main-success-scenario-mss:MSS ]]:

1.  TA requests to list students
2.  ClassMonitor shows a list of students
3.  TA requests to delete a specific student in the list
4.  ClassMonitor deletes the student

    Use case ends.

Extensions:

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. ClassMonitor shows an error message.

      Use case resumes at step 2.

#### Use case 2: Add a student

[[ main-success-scenario-mss:MSS ]]:

1. TA requests to add a student in the list
2. TA includes the relevant student's info
3. ClassMonitor adds the student

    Use case ends.

Extensions:

* 2a. TA did not follow the correct format as stated in the instructions.
* 2a1. ClassMonitor shows an error message.

  Use case ends.

#### Use case 3: Add a tag to a student

[[ main-success-scenario-mss:MSS ]]:

1.  TA requests to list students
2.  ClassMonitor shows a list of students
3.  TA requests to add a tag to a specific student in the list
4.  ClassMonitor adds the tag

    Use case ends.

Extensions:

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. ClassMonitor shows an error message.

      Use case resumes at step 2.

#### Use case 4: Find a student by name

[[ main-success-scenario-mss:MSS ]]:

1.  TA requests to list students
2.  ClassMonitor shows a list of students
3.  TA searches a specific student in the list
4.  ClassMonitor returns the specific student's details

    Use case ends.

Extensions:

* 2a. The list is empty.

  Use case ends.

* 3a. The given keyword does not match any student's name.

    * 3a1. ClassMonitor shows an error message.

      Use case resumes at step 2.

#### Use case 5: Add a star/bolt to a student

[[ main-success-scenario-mss:MSS ]]:

1.  TA requests to list students
2.  ClassMonitor shows a list of students
3.  TA adds a star/bolt to a specific student in the list
4.  ClassMonitor adds a star/bolt to the student

    Use case ends.

Extensions:

* 2a. The list is empty.

  Use case ends.

#### Use case 6: Find students by a specific field

[[ main-success-scenario-mss:MSS ]]:

1.  TA requests to list students
2.  ClassMonitor shows a list of students
3.  TA requests to filter students by a specific field and criteria
4.  ClassMonitor shows a filtered list of students based on the field and criteria specified.

    Use case ends.

Extensions:

* 2a. The list is empty.

* 3a. The given field does not exist.

    * 3a1. ClassMonitor shows an error message.

      Use case resumes at step 2.

  Use case ends.

#### Use case 7: Sort list of students by a specific field

[[ main-success-scenario-mss:MSS ]]:

1.  TA requests to list students
2.  ClassMonitor shows a list of students
3.  TA requests to sort students by a specific parameter in ascending/descending order
4.  ClassMonitor shows a sorted list of students based on the field and order specified.

    Use case ends.

Extensions:

* 2a. The list is empty.

* 3a. The given field does not exist.

    * 3a1. ClassMonitor shows an error message.

      Use case resumes at step 2.

  Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 students without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  All user operations should be completed within 100 milliseconds.
5.  The project must adhere to a bi-weekly iterative development schedule, ensuring continuous delivery or improvement of a working product every two weeks.

--------------------------------------------------------------------------------------------------------------------
## **Appendix: Planned Enhancements**

The following are planned enhancements that have been proposed by early testers to improve the user experience.

Team size: 5

### Further validation of student details

Some of the properties of a `Student` can be further restricted to be more representative of their real-life counterparts.

* Currently, the `Phone` property is a string with unlimited length. It should have a limit of 8 to 15 characters, since some international phone numbers can go up to 15 characters.
  * Phone numbers in Singapore should start with `8` or `9`.
* `Email` addresses should follow the _local_@_domain_.com format.
* Students should have unique contact details.
  * This means that an `add` or `edit` command will fail if it results in two students having the same `Phone` number and/or `Email`.
* Student `Name`s should not be case-sensitive.
  * This means that `UniqueStudentList` should treat `Tan Ah Beng` and `tan ah beng` as the same student and these two names will never exist simultaneously.
  * One possible implementation is to store `Name` as a lowercase string regardless of how the user enters it, e.g. entering `add n/Tan Ah Beng` will reduce the name to `tan ah beng` before adding it to the list.

Cons: Such additional constraints may restrict the user too much if they want to use the application without hassle.

**Alternative 1**
* Have as toggleable 'Strict Mode' option that the user can enable if they want ClassMonitor to ensure that they cannot enter fields that do not fulfil the limitations.
* Otherwise, only limit fields by string length in 'Unstrict Mode'.

### Enhanced name input
* Allow for ways to input names that are not normally allowed as command parameters, specifically the `/` character in 'S/O'.
* Proposed Change: Have an alternative way to input the name using  `"`.
  * e.g. `add n/"A S/O B" ...` will allow the user to enter a name with a `/`.

### Further input validation for commands
Command inputs can be further restricted to align with the field constraints.
* The `find` command can detect inputs that are out of range (e.g. `find star > 50000` will throw an error because ClassMonitor can never have more than 50000 stars). 

### More granular error messages
Currently, ClassMonitor uses a generic 'try-catch' approach to handling errors in user input and execution. This has some issues:
* Having a generic error message can confuse users as they will not know which part of the input they have entered wrongly.
* The command will throw an error message if it encounters at least one error, which would result in the user having to retry the command repeatedly if there are multiple errors.


**Proposed changes**
1. Have the parser capture the preamble/prefix content that is causing the error and display it to the user.
   1. e.g. `star i/1 s/3` could throw the error `invalid index/preamble 'i/1' given.`.
2. Have the command throw an error message for each option/preamble that is invalid or missing.
   1. e.g. `edit 1 n/s*m p/` will throw an error message with the concatenation for the invalid name and missing phone number.

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

```note
These instructions only provide a starting point for testers to work on; testers are expected to do more *exploratory* testing.
```

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a student

1. Deleting a student while all students are being shown

   1. Prerequisites: List all students using the `list` command. Multiple students in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_

## Glossary

{% include glossary.md type="dg" %}
