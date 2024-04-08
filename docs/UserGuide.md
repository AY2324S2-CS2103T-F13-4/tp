---
layout: page
title: User Guide
---

ClassMonitor is a **desktop app for managing student performances, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ClassMonitor can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Introduction
ClassMonitor is an Student Management System that empowers Teaching Assistants(TA) and Professors to manage their student particulars and obtain insights about their students' performance ratings. As a TA, you can easily view and edit your students' particulars during your daily classes. Utilize ClassMonitor’s flexible tagging system to help you organize your students according to their modules and classes. Finally, streamline your performance grading decisions by deriving insights from your students' performance indicators across time, through ClassMonitor’s statistics – you can allocate stars to students for good performance and bolts for bad performance!

For a more detailed view of ClassMonitor’s features, you can refer to the Features section below!
With a focus on efficiency, you can interact with ClassMonitor without ever reaching for your mouse or moving away from your keyboard! With a focus on user-friendliness, ClassMonitor is easy to learn!

This User Guide provides an in-depth documentation so you can easily use and integrate ClassMonitor into your day-to-day classes as a Professor or TA. It covers how to launch ClassMonitor, core ClassMonitor features and commands, common terms and definitions used in ClassMonitor, and some troubleshooting recommendations. Head over to How to use the User Guide to get started!

## Quick start

1. Ensure you have Java `11` or above installed in your Computer. You can download it [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A).

1. Download the latest `classmonitor.jar` from [here](https://github.com/AY2324S2-CS2103T-F13-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ClassMonitor.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar classmonitor.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui_v1.3.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com m/Computer Science` : Adds a contact named `John Doe` to the list.

   * `star 1 s/3` : Adds 3 stars to the first person on the list.

   * `bolt 1 b/3` : Adds 3 bolts to the first person on the list.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a student to contact book: `add`

> Adds a student to the list.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL m/MAJOR [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have any number of tags (including 0)
</div>

Examples:

**Command Input**: `add n/John Doe p/98765432 e/johnd@u.nus.edu m/Computer Science`

**Assumptions**:
* A student by the name of `John Doe` (case-sensitive) does not already exist. 

**Command Output**: `New student added: John Doe; Phone: 98765432; Email: johnd@u.nus.edu; Major: Computer Science; Tags: `

### Listing all students : `list`

>Shows a list of all students in the list.

Format: `list`

### Editing a student's information : `edit`

> Edits an existing student in the list.

**Format:** `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [m/MAJOR] [s/STAR] [b/BOLT] [t/TAG]…​`

**Info:**
* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. ​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.


**Note:**
* `INDEX` **must be a positive integer** (e.g. 1, 2, 3, …) ​
* `STAR` **must be a positive integer between 1 and 50,000** (e.g. 1, 2, ..., 50,000) ​ 
* A student can only be awarded a maximum of 50,000 stars, with a minimum of 0 stars. ​ 

**Tip:**
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all the student’s tags by typing `t/` without specifying any tags after it.
* Editing the number of stars of a student replaces the existing value.


**Examples:**
*  `edit 1 p/98765432 e/johndoe@u.nus.edu` Edits the phone number and email address of the 1st student to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd student to be `Betsy Crower` and clears all existing tags.
*  `edit 3 s/0 b/0` Edits both the total number of stars and the total number of bolts received by student to be `0`. 

### Awarding a student stars for participation : `star`

> Awards a student stars for good participation in class.

**Format:** `star INDEX s/STAR`

**Info:**
* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. ​ 
* `STAR` refers to the number of stars to be awarded to the student. ​ 


**Note:**
* `INDEX` **must be a positive integer** (e.g. 1, 2, 3, …)
* `STAR` **must be a positive integer between 1 and 10** (e.g. 1, 2, ..., 10)
* The `STAR` will add onto existing number of stars the student already has.
* A student can only be awarded a maximum of 50,000 stars, with a minimum of 0 stars.

**Example:**

**Command Input**: `star 1 s/5`

**Assumptions**:
* Alex Yeoh is the student at Index 1
* He currently has 0 stars

**Command Output**: `Added stars to Student: Alex Yeoh; [...]`

![star added example](images/star.png)

### Awarding a student bolts for being absent : `bolt`

> Awards a student bolts for being absent in class.

**Format**: `bolt INDEX b/BOLT`

**Info:**
* The number of bolts corresponds to the number of times the student is absent. ​
* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. ​ 
* `BOLT` refers to the number of bolts to be awarded to the student. ​ 


**Note:**
`INDEX` **must be a positive integer** (e.g. 1, 2, 3, …) ​ 
* `BOLT` **must be a positive integer between 1 and 10** (e.g. 1, 2, ..., 10) ​ 
* The `BOLT` will add onto existing number of bolts the student already has. ​ 
* A student can only be awarded a maximum of 50,000 bolts, with a minimum of 0 bolts. ​ 


**Example:**

**Command Input**: `bolt 1 b/5`

**Assumptions**:
* Alex Yeoh is the student at Index 1
* He currently has 0 bolts

**Command Output**: `Added bolts to Student: Alex Yeoh; [...]`

![bolt added example](images/bolt.png)

### Find all students by an attribute: `find`


> Finds all students who fulfill the given criteria for the specified field.

**Format**: `find FIELD CRITERIA`

**The parameters for `CRITERIA` depends on the `FIELD` specified:**
* `find name KEYWORD [MORE KEYWORDS]`: Finds students with names that match **any** of the given keywords.
    * The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
    * Only full words will be matched. e.g. `Han` will not match `Hans`
    * Students matching at least one keyword will be returned (i.e. `OR` search).
      e.g. `find name Hans Bo` will return `Hans Gruber`, `Bo Yang`
* `find major KEYWORD`: Finds students with majors that **contain** the given keyword.
    * e.g. `find major Science` will return `Computer Science` majors and `Science` majors.
* `find star OPERATOR NUMBER`: Finds students with stars within the bounds as specified by the given operator and number.
    * e.g. `find star = 0` will return students with **0** stars.
* `find bolt OPERATOR NUMBER`: Finds students with bolts within the bounds as specified by the given operator and number.
    * e.g. `find bolt < 5` will return students with less than 5 stars.
* `find tag KEYWORD`: Finds students with tags that **contain** the given keyword.
    * e.g. `find tag friend` will return students that are tagged `friend` or `friends`.

**Info:**
* Accepted fields are `name`, `major`, `star`, `bolt`, and `tag`.
* Accepted operators for `find star` and `find bolt` are:
  * < (strictly less than)
  * <= (less than or equal to)
  * \> (strictly more than)
  * \>= (more than or equal to)
  * = (equal to)
* Matching is case-insensitive.

**Examples**:

**Command Input**: `find name alex david`

**Assumptions**:
* **Alex Yeoh** is the only student whose name includes a word that matches `alex`.
* **David Li** is the only student whose name includes a word that matches `david`.

**Command Output**: `2 students listed!`
* `find name alex david` returns `Alex Yeoh`, `David Li`


<img src="images/findNameAlexDavidResult.png" alt="result for 'find name alex david'" style="width: 400px; height: 160px;">


### Sort all students by a field: `sort`

> Displays students on ClassMonitor in a sorted order based off a given field either in ascending or descending order.

**Format**: `sort FIELD ORDER`

**Info**:
* The only accepted fields are `name`, `phone`, `email`, `major`, `star`, `bolt`
* The only accepted orders are `asc` for ascending order of the specific field or `desc` for descending order of the specific field
* The field and orders can have a combinations of capital and lower case letters
  e.g. `sort name ASc` and `sort Major desc` are valid
* For tie breaking between students that have the same value for a given field, name will be used as a tiebreaker
 

**Examples**:

**Command Input**:`sort bolt desc`

**Assumptions**:
* ClassMonitor contains three students **Alex Yeoh** with 3 bolts, **Bernice Yu** with 3 bolts, and **David Li** with 0 bolts

**Command Output**: `Sorted all persons by bolt in descending order.`
* `sort bolt desc` displays **David Li** on the top of the students displayed, then **Alex Yeoh**, followed by **Bernice Yu**

<img src="images/sortBoltDesc.png" alt="result for 'sort bolt desc'" style="width: 400px; height: 160px;">

### Deleting a student's particulars : `delete`

Deletes the specified student from the list.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed students list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd student in the list.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ClassMonitor data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ClassMonitor data are saved automatically as a JSON file `[JAR file location]/data/classmonitor.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ClassMonitor will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the ClassMonitor to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ClassMonitor home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

**All eve**

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL m/MAJOR [t/TAG]…​` <br> e.g., `add n/James Ho p/83482048 e/jamesho@u.nus.edu m/Computer Science t/CS2103T t/G18`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [m/MAJOR] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@u.nus.edu m/Psychology`
**Star** | `star INDEX s/STAR` <br> e.g., `star 1 s/5`
**Bolt** | `bolt INDEX b/BOLT` <br> e.g., `bolt 1 b/5`
**Find** | `find FIELD CRITERIA`<br> e.g., `find name alex david`
**Sort** | `sort FIELD ORDER`<br> e.g., `sort star asc`
**List** | `list`
**Help** | `help`
**Exit** | `exit`
