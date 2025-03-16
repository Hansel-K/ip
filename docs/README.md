# Capy Task Manager User Guide

![Product Screenshot](docs/CapyScreenshot.png)  
*Insert a product screenshot here.*

## Description
Capy is a simple yet powerful task management application designed to help users organize and track their tasks 
effectively. With its intuitive command-line interface, users can add, delete, search, and manage tasks like 
ToDos, Deadlines, and Events. It also supports persistent storage, so your tasks are always saved.

---

## Quick Start

1. **Install Java**: Ensure you have Java 17 or above installed on your computer.
2. **Download the Application**: Download the latest `.jar` file from [here](#). *(Provide the actual link.)*
3. **Set Up**: Copy the `.jar` file to the folder you want to use as the home folder for Capy Task Manager.
4. **Run the Application**:
    - Open a command terminal.
    - Navigate (`cd`) into the folder where you placed the `.jar` file.
    - Run the application with:
      ```bash
      java -jar capy.jar
      ```
5. **Using the Application**: The application is now running, and you can start entering commands!

---

## Key Features
- **ToDo**: Add a task without any time or due date.
- **Deadline**: Add a task with a specific due date or time.
- **Event**: Add a task with a defined start and end date/time.
- **View All Tasks**: Display the list of all tasks saved.
- **Mark Task as Done**: Mark a task as completed.
- **Unmark Task as Not Done**: Unmark a task as incomplete.
- **Delete Task**: Remove a task from the list of tasks saved.
- **Find Tasks**: Find tasks by keywords and display a list of tasks with matching keywords.
- **Exit Program**: Terminate the program.

## Command Format

### Notes:
- Words in **UPPER_CASE** are placeholders for user-supplied parameters.
  Example: `todo TASK_A` means the `todo` command and `TASK_A` is the task description you provide, such as 
  `todo CS2113 Quiz`.

---

## Managing Tasks

### 1. Adding a ToDo Task: `todo`
Adds a task without any time or due date.

Format:
```bash
todo TASK
```

Example Input:
```bash
todo CS2113 Quiz
```

Expected Output:
```bash
Got it. I've added this task:
  [T][ ] Read a book
Now you have 1 tasks in the list.
```
---

### 2. Adding a Deadline Task : `deadline`
Add tasks with a specific due date or time.

Format:
```bash
deadline TASK /by DUE_DATE
```

Example Input:
```bash
deadline CS2113 Quiz /by Friday 1159
```

Expected Output:
```bash
Got it. I've added this task:
  [D][ ] CS2113 Quiz (by: Friday 1159)
Now you have 2 tasks in the list.
```

---

### 3. Adding an Event Task: `event`
Add tasks with a defined start and end date/time.

Format:
```bash
event TASK /from START /to END
```

Example Input:
```bash
event CS2113 Tutorial /from Wed 12pm /to 1pm
```

Expected Output:
```bash
Got it. I've added this task:
  [E][ ] CS2113 Tutorial (from: Wed 12pm to: 1pm)
Now you have 3 tasks in the list.
```
---

### 4. View All Tasks: `list`
View all tasks in the list along with their types and statuses.

Format:
```bash
list
```

Example Input:
```bash
list
```

Expected Output:
```bash
Here are the tasks in your list:
1. [T][ ] CS2113 Quiz
2. [D][ ] CS2113 Quiz (by: Friday 1159)
3. [E][ ] CS2113 Tutorial (from: Wed 12pm to: 1pm)
```

---

### 5. Marking a Task as Done: `mark`
Marks the task at the specified index within the task list as completed.

Format:
```bash
mark TASK_INDEX
```

Example Input:
```bash
mark 1
```

Expected Output:
```bash
Nice! I've marked this task as done:
  [T][X] CS2113 Quiz
```

---

### 6. Unmarking a Task as Not Done: `unmark`
Unmarks a task at the specified index within the task list as incomplete.

Format:
```bash
unmark TASK_INDEX
```

Example Input:
```bash
unmark 1
```

Expected Output:
```bash
Ok, I've marked this task as not done yet:
  [T][ ] CS2113 Quiz
```

---

### 7. Deleting a Task: `delete`
Deletes a task at the specified index within the task list.

Format:
```bash
delete TASK_INDEX
```

Example Input:
```bash
delete 1
```

Expected Output:
```bash
Noted. I've removed this task:
  [T][ ] CS2113 Quiz
Now you have 2 tasks in the list.
```

---

### 8. Finding Tasks: `find`
Find tasks by keywords and display a list of tasks with matching keywords.

Format:
```bash
find KEYWORDS
```

Example Input:
```bash
find CS2113 Quiz
```

Expected Output:
```bash
Here are the matching tasks in your list:
1. [D][ ] CS2113 Quiz (by: Friday 1159)
```

---

### 9. Exiting the Program: `bye`
Terminate the program.

Format:
```bash
bye
```

Example Input:
```bash
bye
```

Expected Output:
```bash
Bye. Hope to see you again soon!
```

---

## Error Handling

### Invalid Commands
If the program does not recognise a command, it will return the following error message:

```bash
Oops! Command not Recognised! Please try a valid command!
```

### Missing Arguments
If a command is entered without some required arguments (e.g. `todo` without a task description), 
the system will notify you your command is missing some details:

```bash
Oops! Seems like the command is missing some details!
```

#### Notes:
There are also different error messages for specific cases when the missing argument can be identified.

- Missing Description:
```bash
Oops! Seems like the command is missing a description!
```

- Missing Task Number:
```bash
Oops! Seems like the command is missing a task number!
```

### Out-of-Range Task Indexes
If you try to mark, unmark, or delete a task with an index that does not exist within the list of tasks 
(e.g. trying to delete task 5 when there are only 3 tasks), the program will inform you of your mistake:

```bash
Oops! Invalid task number!
```

### Invalid Data Format
For the mark, unmark, and delete commands, if you input a string or decimal in place of an integer for the task index 
(e.g. trying to enter `mark XYZ` or `mark 3.14`), the program will inform you of your mistake:

```bash
Oops! Please enter a valid integer as a task number!
```

---

## Saving Data
Have no fear! Capy Task Manager automatically saves your tasks to a files after any modification!

---

## Editing the Data File
Task data is saved in a capy.txt file. This file can be dited directly, but do ensure that the format remains 
valid to avoid errors when loading the data!

### CAUTION:
If you make changes that corrupt the file format, *all your tasks may be discarded* by Capy Task Manager! 
Capy Task Manager would then start fresh with an empty list instead, so be careful when making edits to the data file!

---

## Command Summary

| Action                    | Command Format                                      |
|---------------------------|-----------------------------------------------------|
| Add a Todo Task           | `todo <TASK_DESCRIPTION>`                           |
| Add a Deadline Task       | `deadline <TASK_DESCRIPTION> /by <DUE_DATE>`        |
| Add an Event Task         | `event <TASK_DESCRIPTION> /from <START> /to <END>`  |
| List All Saved Tasks      | `list`                                              |
| Mark a Task as Done       | `mark <TASK_NUMBER>`                                |
| Unmark a Task as Not Done | `unmark <TASK_NUMBER>`                              |
| Delete a Task             | `delete <TASK_NUMBER>`                              |
| Find Tasks                | `find <KEYWORD>`                                    |
| Exit the Program          | `bye`                                               |
