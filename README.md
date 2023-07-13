# bts-it Programming challenge
## Requirements:
### Problem  - Find the treasure

A treasure map was uncovered, but the map only shows the starting location. The path from the origin to the treasure is given as series of steps.
 
***"Walk north for 2 hours, then ride a horse at trot speed heading east for 15 minutes..."***

Multiple teams of treasure hunters have departed to follow the map's instructions. **Your** team decided, based on the fact that there are more than 1000 instructions, that you are better off parsing the map with a computer and then just following a straight line towards the general area of the treasure.
### Generalizations
You made some generalizations on the speed of the modes of transportation and came up with the following approximations (default speeds)
- Walk          - 3 mph
- Run           - 6 mph
- Horse trot    - 4 mph
- Horse gallop  - 15 mph
- Elephant ride - 6 mph
Another member of the team has already written an API to translate the map into REST API which is ready for your program: http://localhost:8080/speed-list

## Task
Write a program to parse the following input to determine the location of the final destination.

**Example input:**
- Walk, 20 mins, N
- Run, 1 hour, E
- Horse trot, 3 hours, NW
- Elephant ride, 1 hour 30 mins, SE
- Horse gallop, 20 mins, SE
- Walk, 30 mins, SW
- Horse trot, 1 hour 1 min, W
- ...

**Example output:**
- 2029 miles to the north, 298893 miles to the east

## Implementation Details:
Utilize existing in-memory database to manage/maintain tables that will store the Default Speeds and Directions

### Implement the following actions using the architecture layers from the sample project
#### Finder tab:
**Save** - Save the details into the table on the left (don’t persist to DB) Compute the distance based on entries in table to Treasure and display value under the table

**Cancel** – Clear any entries and default the combos to the 1st entry

**Delete** - Remove the selected entry from the table on the left

**Rules** - Compute the distance based on entries in table to Treasure and display value under the table. 

**Validations**
- Perform validations – when saving – all fields should be filled in – otherwise notify user.
- Perform validations – when deleting – an entry must be selected from table – otherwise notify user
<!-- 
Maintenance Tab
When tab comes to focus, display all available Step/Speed combinations from DB
Save – Save the step/speed values into the DB, display in view (both values are required – warn user if not provided)
Cancel – clear the Step/Speed text boxes
Delete – Delete the highlighted entry (validate that if nothing is highlighted – warn user)
-->

# Technical Spec:
- Create a RESTful web service 
-- One end point for Finder
<!-- 
-- One end point for Maintenance 
-->
- All logic should be on the server side
  - Save (Finder) – compute distance
  - Delete (Finder) – compute distance
<!--
  - Save (Maintenance) – Save to DB
  - Delete (Maintenance) – Delete from DB
-->
- Loading the Finder tab – refresh the values from the combo – load from DB
- Logic should be implemented using the layered architecture as in the Sample Project
<!--
DAO – should be package protected
Internal Service – in same package as DAO
-->

