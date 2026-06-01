# TaskFlow 

TaskFlow is a robust, console-based Java SE application designed for enterprise Work and Leave Management. Built with a clean separation of concerns adhering to the Model-View architecture pattern, TaskFlow manages employees, routes tasks, logs task status history, distributes notifications, and processes leave requests—all simulated in-memory using central repositories.

---

##  Architecture & Design Patterns

The codebase is organized cleanly to separate data, presentation, and business logic:

1. **Model-View Architecture Pattern**:
   - **Views (`*View.java`)**: Responsible exclusively for console-based user interface interactions, terminal prompts, and receiving input from the user scanner.
   - **Models/ViewModels (`*Model.java` / `*ViewModel.java`)**: Handle business logic, input validation (e.g., regex-based email checks, date-range validations), and call back to their associated views.
2. **Repository Layer (Single Source of Truth)**:
   - [TaskFlowDB](file:///d:/Working/WorkManagement/taskflow/src/com/sathish/taskflow/data/repository/TaskFlowDB.java) and [LeaveRepository](file:///d:/Working/WorkManagement/taskflow/src/com/sathish/taskflow/data/repository/LeaveRepository.java) implement the **Singleton pattern** to manage collections of DTOs dynamically, acting as a lightweight in-memory database.
3. **Data Transfer Objects (DTOs)**:
   - Decoupled domain models storing structure and state.

---

## 🔑 Registration & Input Validation Rules

Since TaskFlow utilizes an in-memory database simulator, **no pre-loaded default accounts exist upon fresh startup**. All data is created dynamically. 

To sign in, you must first register via **Option 1: Sign Up** on the landing menu. Please ensure your inputs align with the following validation rules:

| Field | Input Rule / Regex Constraint | Example                | Note |
| :--- | :--- |:-----------------------| :--- |
| **Full Name** | Length must be between 3 and 50 characters. | `Sathish`              | Leading/trailing spaces are trimmed. |
| **Email** | `^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$` | `manager@taskflow.com` | Used as the login ID. Must be unique. |
| **Password** | `^(?=.*[A-Za-z])(?=.*\d).{8,}$` | `Password123`          | Minimum 8 characters; must contain letters & numbers. |
| **Mobile No** | `^[6-9]\d{9}$` | `9876543210`           | Must be a valid 10-digit number starting with 6-9. |
| **Date of Birth**| `dd-MM-yyyy` | `25-12-1998`           | User must be at least 18 years old. |
| **Role** | Selection of Manager (1) or Employee (2). | `1` or `Manager`       | **First user is automatically assigned Manager role.** |

> [!NOTE]
> Since employees must report to a manager, you cannot register an **Employee** account if there are no registered managers in the system. The very first user of the application must register as a **Manager**.

### 💡 Quick Setup Demo Credentials

To test the application quickly without failing any validation rules, register these sample accounts in order:

#### 1. Register the Manager Account (First Registration)
When prompted in **Sign Up**, type:
* **Full Name**: `Sathish`
* **Email**: `sathish@taskflow.com`
* **Password**: `Password123`
* **Confirm Password**: `Password123`
* **Mobile Number**: `9876543210`
* **Date of Birth**: `15-08-1995`
*(Note: As the first user, you are automatically set as a Manager)*

#### 2. Register the Employee Account (Second Registration)
Sign out or open registration again, then type:
* **Full Name**: `Ramesh`
* **Email**: `ramesh@taskflow.com`
* **Password**: `Password123`
* **Confirm Password**: `Password123`
* **Mobile Number**: `9876543211`
* **Date of Birth**: `10-10-2000`
* **Role**: Choose `2` (Employee)
* **Reporting Manager**: Select `1. Sathish`

---

## 🌟 Key Features & Capabilities

TaskFlow provides distinct dashboard experiences tailored to two primary user roles:

### 💼 Manager Features
* **Employee Directory**: Browse all active corporate employees or view dedicated lists of direct reportees.
* **Organization Management**: Onboard new employees directly from the console interface.
* **Task Lifecycle Management**:
  * Create new tasks specifying Title, Description, Priority (`P1`, `P2`, `P3`), and Due Date.
  * Assign open tasks to team members.
  * Track real-time team status and progress.
* **Leave Approvals**: View, approve, or reject pending leave requests submitted by reportees.
* **Audit Logs**: Access detailed historical logs of task status changes including remarks and timestamps.
* **Analytical Reports**: Review high-level summaries of task completion and organizational performance.

### 👤 Employee Features
* **My Dashboard**: View tasks explicitly assigned to them.
* **Task Interaction**:
  * Update task status (e.g., transition between `OPEN`, `IN_PROGRESS`, `COMPLETED`, `ON_HOLD`, `CANCELLED`, `REOPENED`).
  * Reassign tasks to other active team members when necessary.
  * Access comprehensive task details, audit history, and manager remarks.
* **Leave Management**: Apply for leaves by specifying start date, end date, and reason, with real-time status tracking (`PENDING`, `APPROVED`, `REJECTED`).
* **Real-time Notifications**: Instantly receive in-app alerts for newly assigned tasks, status changes, and reminders.

---

## 📂 Project Structure

Here is the exact, complete file structure of the **TaskFlow** project:

```text
taskflow/
├── taskflow.iml
├── README.md
└── src/
    └── com/
        └── sathish/
            └── taskflow/
                ├── TaskFlowApplication.java
                ├── data/
                │   ├── dto/
                │   │   ├── Employee.java
                │   │   ├── Leave.java
                │   │   ├── LoginRequest.java
                │   │   ├── Notification.java
                │   │   ├── Task.java
                │   │   └── TaskStatusHistory.java
                │   └── repository/
                │       ├── LeaveRepository.java
                │       └── TaskFlowDB.java
                ├── util/
                │   ├── ConsoleInput.java
                │   └── ParseHelper.java
                └── features/
                    ├── home/
                    │   ├── HomeModel.java
                    │   └── HomeView.java
                    ├── signin/
                    │   ├── SignInModel.java
                    │   └── SignInView.java
                    ├── signup/
                    │   ├── SignUpModel.java
                    │   └── SignUpView.java
                    ├── leave/
                    │   ├── LeaveView.java
                    │   └── LeaveViewModel.java
                    ├── notification/
                    │   ├── NotificationModel.java
                    │   └── NotificationView.java
                    ├── report/
                    │   ├── Report.java
                    │   ├── ReportModel.java
                    │   └── ReportView.java
                    ├── employee/
                    │   ├── EmployeeListModel.java
                    │   ├── EmployeeListView.java
                    │   ├── add/
                    │   │   ├── EmployeeAddModel.java
                    │   │   └── EmployeeAddView.java
                    │   ├── details/
                    │   │   ├── EmployeeDetailsModel.java
                    │   │   └── EmployeeDetailsView.java
                    │   └── reportee/
                    │       ├── ReporteeListModel.java
                    │       └── ReporteeListView.java
                    └── task/
                        ├── assign/
                        │   ├── AssignMode.java
                        │   ├── TaskAssignModel.java
                        │   └── TaskAssignView.java
                        ├── create/
                        │   ├── TaskCreateModel.java
                        │   └── TaskCreateView.java
                        ├── detail/
                        │   ├── TaskDetailModel.java
                        │   └── TaskDetailView.java
                        ├── list/
                        │   ├── TaskListModel.java
                        │   └── TaskListView.java
                        ├── status/
                        │   ├── TaskStatusUpdateModel.java
                        │   └── TaskStatusUpdateView.java
                        └── team/
                            ├── TeamStatusModel.java
                            └── TeamStatusView.java
```

---

## 🗃️ Data Model & Enums

### Employee
- **Attributes**: `id`, `employeeId`, `name`, `email`, `password`, `mobileNo`, `dob`, `role`, `reportingTo`, `status`, `createdAt`
- **Role**: `MANAGER`, `EMPLOYEE`
- **Status**: `ACTIVE`, `INACTIVE`

### Task
- **Attributes**: `id`, `title`, `description`, `assignedBy`, `assignedTo`, `priority`, `createdTime`, `dueDate`, `updatedTime`, `completedTime`, `remarks`, `status`
- **Priority**: `P1`, `P2`, `P3`
- **Status**: `OPEN`, `IN_PROGRESS`, `COMPLETED`, `ON_HOLD`, `CANCELLED`, `REOPENED`

### Leave
- **Attributes**: `id`, `employeeId`, `startDate`, `endDate`, `reason`, `status`, `appliedOn`
- **Status**: `PENDING`, `APPROVED`, `REJECTED`

### Notification
- **Attributes**: `id`, `employeeId`, `taskId`, `message`, `type`, `isRead`, `createdTime`
- **Type**: `TASK_ASSIGNED`, `STATUS_UPDATED`, `DUE_REMINDER`

### Task Status History
- **Attributes**: `id`, `taskId`, `changedBy`, `oldStatus`, `newStatus`, `remarks`, `changedTime`

---

## 🚀 Getting Started

### Prerequisites
* Java SE Development Kit (JDK) 8 or higher installed.

### Compilation
From the `taskflow` directory, run:
```bash
# Create directory for compiled class files
mkdir -p out/production/taskflow

# Compile all source files
javac -d out/production/taskflow -sourcepath src src/com/sathish/taskflow/TaskFlowApplication.java
```

### Execution
Run the compiled application using:
```bash
java -cp out/production/taskflow com.sathish.taskflow.TaskFlowApplication
```

---

## 🔮 Future Scope

The following features and enhancements are planned for future releases of TaskFlow:

* **Persistent Database Integration**: Replace the in-memory Singleton database (`TaskFlowDB`) with a relational database (e.g., MySQL, PostgreSQL) utilizing JDBC or Hibernate/JPA.
* **Modern Web Interface**: Migrate from a console application to a full-stack web application (e.g., using Spring Boot, React, or Vue) to provide a rich graphical dashboard.
* **Enhanced Authentication & Security**: Implement cryptographic hashing (BCrypt) for user passwords and support OAuth2/JWT stateless user sessions.
* **Advanced Leave Management Policies**: Support multiple leave categories (Casual, Sick, Privilege) with custom annual quotas, accrual rates, and automated carry-forward checks.
* **Real-time SMS & Email Integration**: Send automated email and SMS alerts (via SMTP, Twilio, or SendGrid) for critical task updates, reminders, and leave status changes.
* **Task Collaboration Tools**: Add support for task comments, subtasks, file attachments, and dependency mapping.