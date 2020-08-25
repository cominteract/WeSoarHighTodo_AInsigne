# WeSoarHighTodo_AInsigne

# This is a todo app made specifically for an exam
# The application starts of with a list of the tasks retrieved from the api provided. In ascending order
# On the right side of the task is a delete button for deleting the task 
# and a completion check for marking the task as completed without updating its title
# Since the api is only mocked i installed room to save its data
# While i did still call the api for delete, put and insert it doesn't do much since its mocked
# so installing room did the job of the crud operations
# From the task list clicking a single task brings up the detail view where you can update its title, date and whether it is completed.
# You can also add a task from the plus button at the top it brings up the detail view where you can update its title, date and whether it is completed.
# After either updating or inserting a task. It will go back to the task list with the task list updated

# I also added unit testing and adding a fake build for testing without relying on the api
# The fake build can be adjusted on the build variants
# For testing with the api choose apiDebug
# For testing with the fake choose fakeDebug
