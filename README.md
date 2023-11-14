# Blog_Website
This is java based fullstack web application we use these technologies  React, Spring Boot, MySQL and its a team project
SOFTWARE
REQUREMENT
SPECIFICATION

For

BLOG WEBSITE AND APPLICATION

Prepared By:
Muthuraj M
Ajith R
Nedunchezhian M
Kamaleshwaran K

Academic Year: 2023-2024

Department: Computer Science and
      Engineering
1. Introduction

1.1 Purpose
	The main objective of this document is to illustrate the requirements of the project blog website. The document gives the detailed description of both functional and non-functional requirements proposed by the client. The purpose of this project is to provide a friendly environment to blog for the peoples. The main purpose to this project is to maintain connection between the blogger and the people to ensure the both of them into blogging. This project describes the hardware and software interface requirements using ER diagrams and UML diagrams.
1.2 Scope and Development of Project
	The scope of the project is to broadcast the blog website and going to be a online blogging platform, it will help to blog about anything by the users and to provide a friendly environment.
	The limitation of this blog website is it does not support audio conversations. To overcome this limitation, we are concurrently working on developing better technologies.
	The fact that the website uses an internal network setup within the organization makes it very secure from outside attacks.
1.3 Definitions, Acronyms and Abbreviations
•	CMS - Content Management System: A software application used to manage and organize digital content, such as articles and images on a website.
•	QA - Quality Assurance: The process of ensuring that the website meets specified requirements and quality standards.
•	CI/CD - Continuous Integration/Continuous Delivery: Development practices that involve regularly merging code changes and automating the testing and deployment processes. 
•	GDPR - General Data Protection Regulation: European Union regulations that govern the handling of personal data and privacy.
•	UMS - User Management System: A system for managing user information and interactions. 
•	URM - User Relationship Management: Strategies for managing interactions and relationships with users. 
•	SEO - Search Engine Optimization: The practice of optimizing a website to improve its visibility and ranking in search engine results. 
•	KPI - Key Performance Indicator: Metrics used to measure the performance and success of the blog website.



Acronyms and Abbreviations
•	SRS: Software Requirements Specification
•	HTTPS: Hypertext Transfer Protocol Secure
•	API: Application Programming Interface
•	UI: User Interface
•	UX: User Experience
•	SQL: Structured Query Language
•	HTML: Hypertext Markup Language
•	CSS: Cascading Style Sheets
•	DNS: Domain Name System
•	SMTP: Simple Mail Transfer Protocol
•	HTTP: Hypertext Transfer Protocol
•	TCP/IP: Transmission Control Protocol/Internet Protocol
•	JSON: JavaScript Object Notation
1.4 References
	Book:
•	"Software Requirements" by Karl E. Wiegers and Joy Beatty: This book provides a comprehensive guide on gathering, documenting, and managing software requirements.
Messaging Protocols:
•	WebSocket Protocol: If your chatting application uses WebSocket for real-time communication, reference the WebSocket RFC (RFC 6455) or relevant documentation.
•	User Authentication Standards: OAuth 2.0 and OpenID Connect: If your application    involves user authentication, reference these standards for authentication and authorization.
Security Standards:
•	OWASP Top Ten: The Open Web Application Security Project (OWASP) publishes a list of the top ten web application security risks. Reference this for security considerations.
•	ISO/IEC 27001: If your project has specific security requirements, refer to the ISO/IEC 27001 standard for information security management systems.





2. Overall Descriptions
2.1 Product Perspective
2.4 User Classes and Characteristics
The system provides different types of services based on the type of users.
1.	Registered Use: Individuals who have created accounts on the platform.
2.	Administrators: Moderators and administrators responsible for monitoring and   managing content and user accounts.
3.	Guest Users: Visitors who can access limited features without registration.

2.5 Assumptions and Dependencies
Assumptions:
•	The system assumes that users have access to a stable internet connection for real-time interactions.
•	Internet Connectivity: It is assumed that users will have access to a stable internet connection for real-time posting blogs and reading blogs. 
•	User Devices: Users are assumed to have compatible devices (smartphones, tablets, computers) with updated browsers.
•	Data Privacy: Users are expected to understand and agree to the website's privacy policy and terms of service.
•	Security Awareness: Users are assumed to follow best practices for password security and not share their login credentials.
•	Content Moderation: The website may rely on user reporting for content moderation, assuming that users will help enforce community guidelines.
•	Server and Hosting: There is an assumption that server infrastructure or cloud hosting is available and properly configured for the website's needs.
•	Compliance with Regulations: The website is expected to comply with relevant data protection laws and regulations (e.g., GDPR, CCPA) as applicable.
Dependencies:
•	Third-Party Services: The website may depend on external services for authentication, social login, cloud storage, or multimedia sharing. These services must be available and operational.
•	Development Frameworks: The availability and stability of development frameworks, libraries, and tools used in the website's development.
•	Network Infrastructure: The stability and performance of the internet and network infrastructure can affect the interactions on the website.
•	Server and Hosting Providers: The reliability and availability of the chosen server infrastructure or cloud hosting provider.
•	Database Systems: The stability and performance of the database system used for storing user data and blog and reading history.
•	Security Updates: Dependencies on security patches and updates for the underlying technologies to address vulnerabilities.
2.6 Requirement
Software Configuration: This software package is developed using java as front end which is supported by NetBeans system. Microsoft SQL Server as the back end to store the database. 
Operating System: Windows NT, windows 98, Windows XP Language: Java Runtime Environment, Net beans 7.0.1 (front end) 
Database: MS SQL Server (back end)
Hardware Configuration:
•	Processor: Pentium(R)Dual-core
•	CPU Hard Disk: 40GB RAM: 256 MB or more
 
2.7 Data Requirement
1.	User data :
•	User profiles
•	Authentication data
•	User preferences
•	Following lists
•	Followers list
2.	Blogging Data:
•	Blog content
•	Blog status
•	Blog history
•	Blog groups
3.	Metadata:
•	Blog metadata
•	User metadata
4.	Notification Data :
•	Push notification tokens
5.	User Activity:
•	User activity logs
•	User engagement data
6.	File Storage:
•	Multimedia files
•	File metadata
7.	Authentication and Security Data:
•	Password hashes
•	Authentication tokens
•	Security logs
8.	Content Moderation Data:
•	Reported content.
•	Moderation actions
9.	Analytics and Metrics Data:
•	Usage analytics
•	Performance metrics
10.	Location Data (if applicable):
•	User location
•	Location history

 
3. Functional and Non-Functional Requirements
3.1 Functional Requirements 
3.1.1 User Registration and Authentication:
•	Users can create accounts with unique usernames and passwords.
•	Users can log in and log out securely.
•	Support for social media login (e.g., Google, Facebook).
3.1.2 Blogging Features:
•	Real-time blog posting of users.
•	Ability to post about user interests.
•	Ability to send multimedia messages (images, videos, voice notes).
•	Group Discussion functionality.
•	Typing indicators and read receipts.
•	Content search and retrieval.
•	Emoji support.
3.1.3 User Profile Management:
•	Users can create, edit, and delete their profiles.
•	Profile includes display name, status message, and contact information.
•	Privacy settings for profile visibility.
3.1.4 Notifications:
•	Push notifications for new uploads and updates.
3.1.5	Search and Discoverability:
•	Search for users and blogs.
•	Discover public discussion room and communities.
3.1.6	Reporting and Moderation:
•	Users can report inappropriate content or users.
•	Moderation features for administrators and moderators.
3.1.7	Compatibility and Cross-Platform Support:
•	Support for multiple platforms (web, iOS, Android).
•	Consistent user experience across platforms.
3.1.8	Performance and Scalability:
•	Low-latency content delivery.
•	Ability to handle a large number of concurrent users.
•	Efficient use of server and network resources.


3.1.9	Security:
•	Secure user authentication and authorization.
•	Protection against common security threats (e.g., XSS, CSRF).
•	Password recovery and reset options.
3.1.10 Content Sharing:
•	Support for sharing files and documents.
•	Media compression for faster sharing.
•	Integration with cloud storage services (e.g., Dropbox, Google Drive).
3.2 Non-Functional Requirements:
3.2.1 Usability:
•	Intuitive user interface with easy navigation.
•	Consistent design and layout.
•	Accessibility features for users with disabilities.
3.2.2	Performance:
•	Fast content delivery and real-time updates.
•	Minimal system resource usage (CPU, memory).
•	Quick response times for user actions.
3.2.3	Reliability:
•	High availability and uptime.
•	Minimal system crashes or downtime.
3.2.4	Security:
•	Data encryption in transit and at rest.
•	Protection against data breaches and unauthorized access.
•	Regular security audits and updates.
3.2.5	Scalability:
•	Ability to scale horizontally to accommodate growing user base.
•	Load balancing for even distribution of traffic.
3.2.6	Maintainability:
•	Easy website updates and bug fixes.
•	Clear and well-documented codebase.
•	Version control and change management.
3.2.7	Performance Monitoring:
•	Monitoring tools for tracking website performance.
•	Alerting and reporting of performance issues.

4. External Interfaces
4.1 Authentication Interfaces:
•	Third-Party Authentication Services: Integration with social media platforms (e.g., Facebook, Google) for user authentication and registration.
•	Authentication APIs: Interaction with authentication APIs (e.g., OAuth 2.0) for secure login and user identity verification.
4.2 Push Notification Services:
•	Apple Push Notification Service (APNs): For sending push notifications to iOS devices.
•	Firebase Cloud Messaging (FCM): For sending push notifications to Android devices.
•	Pusher, One Signal, or Similar Services: Integration with third-party push notification services for real-time notifications.
4.3 Cloud Services:
•	Cloud Storage Services: Interaction with cloud storage services (e.g., AWS S3, Google Cloud Storage) for storing multimedia files, user data, and backups.
•	Serverless Functions: Use of serverless functions (e.g., AWS Lambda) for specific server-side tasks or processing.
4.4 Database Interfaces:
•	Database Management Systems (DBMS): Interaction with specific database systems (e.g., MySQL, PostgreSQL, MongoDB) for data storage and retrieval.
•	Database Connection APIs: APIs or libraries for connecting to and querying the database securely.
4.5 APIs for Multimedia Sharing:
•	Camera and Gallery APIs: Interaction with device cameras and image galleries for multimedia sharing.
•	Video and Voice Calling APIs: Integration with APIs for enabling video and voice calling feature.
5. Hardware Interfaces
5.1 Server Hardware
Description: The system shall be hosted on physical or virtual servers with sufficient computing resources to handle user requests and data storage.
Requirements:
•	Server hardware shall be scalable to accommodate increasing user loads.
•	Redundancy and failover mechanisms shall be in place to ensure high availability.


5.2 Storage Devices
Description: The system shall use storage devices (e.g., hard drives, SSDs) to store user-generated content and data.
Requirements:
•	Storage devices shall have adequate capacity to store user data, including text, images, videos, and user profiles.
•	Regular data backups shall be performed for disaster recovery.
6. Software Interfaces
6.1 Database Management System (DBMS)
Description: The system shall use a relational database management system (e.g., MySQL, PostgreSQL) to store and retrieve user data.
Requirements:
•	The DBMS shall support efficient data retrieval for user profiles, posts, and data.
6.2 Web Server Software
Description: The system shall run on web server software (e.g., Apache, Nginx) to handle HTTP requests and serve web pages.
Requirements:
•	The web server shall be configured to support secure connections using SSL/TLS.
•	It shall be capable of handling concurrent user connections efficiently.

7. System Features 
7.1 User Registration and Authentication:
•	User account creation with unique usernames and passwords.
•	Social media login (e.g., Facebook, Google) for quick registration.
•	Email or phone number verification for account security.
7.2 Real-Time Blogging:
•	Instant discussion room for one-on-one conversations.
•	Real-time Content delivery and modification by users.
•	Support for text-based blogs.
7.3 Multimedia Sharing:
•	Ability to upload and receive images, videos, voice notes, and other multimedia files.
•	Multimedia compression for efficient sharing.
7.4 Group Discussion room:
•	Creation of group discussion with multiple participants.
•	Group discussion administration (e.g., adding/removing members, changing group name).
•	Group notifications and uploads.
7.5 Blog History:
•	Storage of blog history for all contents.
•	Search and retrieval of past blogs.
•	Blog archiving and backup.
7.6 Notifications:
•	Push notifications for new blogs and updates.
•	Notification settings customization.
7.7 User Profiles:
•	User profile creation with display name, and status message.
•	Profile editing and customization.
•	Privacy settings for controlling profile visibility.
7.8 Emojis and Stickers:
•	Support for emojis, stickers, and GIFs in Contents.
•	Emoji and sticker packs for customization.
7.9 Typing Indicators:
•	Typing indicators to show when a user is composing a blog.
7.10 Search and Discoverability:
•	Search for users by username or name.
•	Discover public discussion rooms and contents.
•	Explore trending or popular blogs.
7.11 Security:
•	Secure user authentication and authorization.
•	Two-factor authentication (2FA) for added security.
•	Account recovery options.
7.12 Content Sharing and Integration:
•	Integration with cloud storage services (e.g., Dropbox, Google Drive) for file sharing.
•	Content embedding from external sources (e.g., YouTube videos, tweets).


7.13 Performance and Scalability:
•	Fast content delivery and low latency.
•	Scalability to handle a large user base.
•	Efficient use of server and network resources.
7.14 Backup and Restore:
•	Backup and restore options for blog history.
•	Data recovery in case of device or website changes.
8. Glossaries
•	User:  An individual with a registered account who uses the blog website to upload their contents and interests.
•	Blog: A unit of message upload on the blog website and users in the website can search and read the contents.
•	Group Discussion Room: A discussion room where multiple users can engage in discussions simultaneously, typically centred around a common topic or interest.
•	Emoji: Small digital icons or symbols used to express emotions, reactions, or ideas in posts.
•	Sticker: Larger graphical images or animations used in messages to convey emotions or reactions.
•	Notification:  An alert or message sent by the website to inform users about new blogs, trends, or other events.
•	Authentication: The process of verifying a user's identity during login to ensure secure access to their account.
•	Profile:  A user's public information, including their display name, and status message.
•	Report: An action taken by a user to report about another user’s misconduct of the platform by posting about illegal things or violating the blogging regulations.
9. Maintenance
9.1 Routine Maintenance
1.	Regular Updates: The development team shall provide regular updates to the social media website to address bugs, security vulnerabilities, and performance improvements.
2.	Website Patching: Critical security patches and bug fixes shall be applied promptly to ensure system security and stability.
3.	Database Maintenance: Routine database maintenance, including optimization and backups, shall be performed to maintain data integrity.
9.2 Feature Enhancements
1.	Feature Requests: A process shall be in place to collect and prioritize user feedback and feature requests for future enhancements.
2.	New Feature Development: The development team shall plan and implement new features based on user demand and market trends.

- **Muthuraj M** 	- GitHub: [@Muthuraj](https://github.com/Muthurajkrishna)
- **Nedunchezhian M** 	- GitHub: [@Nedunchezhian M](https://github.com/nedunchezhianm)
- **Ajith R** 		- GitHub: [@Ajith](https://github.com/AjithRagupathi)
- **Kamaleshwarn K** 	- GitHub: [@Kamaleshwaran](https://github.com/kamalesh26)
 

DEPLOYMENT LINK - [BLOG WEBSITE](https://blog-yw5w.onrender.com/)

![image](https://github.com/AjithRagupathi/Blog-Website-and-Application/assets/131537248/476105c6-9bc1-4b93-9336-84de5d13e19a)

**WEBSITE SAMPLE HOME PAGE**
![image](https://github.com/AjithRagupathi/Blog-Website-and-Application/assets/131537248/67ac794d-dc04-48a5-992d-7feb3b8059ad)

**BLOG WEBSITE EDITOR PAGE**
![image](https://github.com/AjithRagupathi/Blog-Website-and-Application/assets/131537248/afca499a-ad18-47a4-be52-ddac4f9e8101)
