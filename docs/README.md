# User Guide
Duke is a personal assistant chat bot that helps a person to keep track of various things.

## Features 

### Adding a ToDo task : `todo`
Adds a ToDo task to the tasks list.

Format: `todo TASK_DESCRIPTION`
* Task description must be provided.

Examples:
* `todo read book` adds a task with description `read book`.
* `todo CS2113 iP User Guide` adds a task with description `CS2113 iP User Guide`.
---

### Adding a Deadline task : `deadline` 
Adds a Deadline task with a deadline to the tasks list.

Format: `deadline TASK_DESCRIPTION /by DATE TIME`
* Task description and date must be provided.
* Time is optional.
* `/by` is used to separate description and date/time.

Examples:
* `deadline return book /by 2020-09-16 18:00` adds a deadline task with description `return book`, date `2020-09-16` and time `18:00`.
* `deadline CS2113 tP User Guide /by 2020-09-17` adds a deadline task with description `CS2113 tP User Guide` and date `2020-09-17`.
---

### Adding an Event task : `event` 
Adds an Event task with a date/time to the tasks list.

Format: `event TASK_DESCRIPTION /at DATE TIME`
* Task description and date must be provided.
* Time is optional.
* `/at` is used to separate description and date/time.

Examples:
* `event borrow book /at 2020-09-16` adds an event task with description `borrow book` and date `2020-09-16`.
* `event CS2113 Tutorial /at 2020-09-17 16:00` adds an event task with description `CS2113 Tutorial`, date `2020-09-17` and time `16:00`.
---

### Deleting a task : `delete` 
Deletes a task with the corresponding task number from the tasks list.

Format: `delete TASK_NUMBER`
* Task number must be provided.
* Task number can be retrieved using the `list` command.

Examples:
* `delete 2` deletes a task with task number 2.
* `delete 4` deletes a task with task number 4.
---

### Marking a task as done : `done` 
Marks a task with the corresponding task number as done in the tasks list.

Format: `done TASK_NUMBER`
* Task number must be provided.
* Task number can be retrieved using the `list` command.

Examples:
* `done 2` deletes a task with task number 2.
* `done 4` deletes a task with task number 4.
---

### Finding a task with keyword : `find` 
Finds tasks containing the keyword from the tasks list.

Format: `find KEYWORD`
* Keyword must be provided.

Examples:
* `find book` lists tasks that have contain `book` keyword.
* `find CS2113` lists tasks that have contain `CS2113` keyword.
---

### Listing tasks from task list using date/time : `list` 
List tasks matching date/time.

Format: `list DATE TIME`
* Date and time are optional.
* The whole list will be printed if date and time are omitted.

Examples:
* `list` lists all tasks.
* `list 2020-09-09` lists all tasks that have date `2020-09-09`.
* `list 2020-09-09 18:00` lists all tasks that have date `2020-09-09` and time `18:00`.
---

### Exits Duke : `bye` 
Exits the program.

Format: `bye`

Example(s):
* `bye` exits the program.