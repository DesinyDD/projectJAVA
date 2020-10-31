# PackMan

##### Developed by Panupat Chulaotrakul (6210402488)

## Update (Commits)

#### (01/11/2020)

- **Recent Commit**

    - update PDF file and add UML Class Diagram
    
    - ปรับปรุงแก้ไขไฟล์ PDF ให้สมบูรณ์
    
    - ปรับปรุง README.md
    
    - เพิ่ม UML Class Diagram

#### (28/10/2020)

- [**e8e5888**](https://bitbucket.org/6210402488/6210402488/commits/e8e5888/)
 
    - major update

    - สร้างระบบฐานข้อมูลผ่าน Package `services` โดยใช้ไฟล์ .csv ในการบันทึกข้อมูลของ `models` ทั้ง 3 package คือ `accounts`, `buildings` และ `mails` ให้สามารถอ่าน และเขียนได้

    - สร้าง GUI ของทุกหน้า
    
    - ปรับปรุงแก้ไข Class และสร้าง Class ภายใต้ Package `controllers` เพื่อรองรับการทำงานของไฟล์ .fxml
    
    - ปรับปรุงแก้ไข Package `models` ให้ทำงานได้มีประสิทธิภาพยิ่งขึ้น
    
    - สร้างการแสดงผล และบันทึกรูปภาพจากผู้ใช้งาน

#### (20/10/2020)

- [**019a821**](https://bitbucket.org/6210402488/6210402488/commits/019a821/)

    - create admin page

    - สร้าง GUI ในส่วนของ Administer

#### (05/10/2020)

- [**4066fea**](https://bitbucket.org/6210402488/6210402488/commits/4066fea/)

    - add main menu & account database

    - เพิ่มหน้า Main Menu และระบบฐานข้อมูลของบัญชีผู้ใช้งาน


#### (29/09/2020)

- [**ce2481c**](https://bitbucket.org/6210402488/6210402488/commits/ce2481c/)

    - update package models
    
    - ปรับปรุงแก้ไข Class ภายใต้ Package `models`

#### (26/09/2020)
                                                              
- [**58f28dc**](https://bitbucket.org/6210402488/6210402488/commits/58f28dc/)
 
    - add models
                                                                                
    - นำ bootstrapfx ออกจาก Project

    - เพิ่ม Class ภายใต้ Package `models`

#### (21/09/2020)
                                                                               
- [**e12f10c**](https://bitbucket.org/6210402488/6210402488/commits/e12f10c/)
 
    - add login & register UI
                                        
    - เพิ่ม GUI ของหน้าลงทะเบียน กับหน้าล็อคอิน
                                                                               
#### (17/09/2020)

- [**92ebde1**](https://bitbucket.org/6210402488/6210402488/commits/92ebde1/)
 
    - update main package

    - นำไฟล์ใน Project จัดใส่ Package `packman` ซึ่งเป็น Package หลัก
   
- [**caa625b**](https://bitbucket.org/6210402488/6210402488/commits/caa625b/)
 
    - fix package name & README.md

    - แก้ไขชื่อ Package หลักเป็น `controllers`, `models ` และ `services`
                                                                                                             
    - แก้ไขไฟล์ README.md ให้มีชื่อผู้จัดทำ

#### (13/09/2020)

- [**7b0507e**](https://bitbucket.org/6210402488/6210402488/commits/7b0507e/)
 
    - add maven to project
    
    - เพิ่ม Framework ของ Maven เข้าสู่ Project

- [**7d97b75**](https://bitbucket.org/6210402488/6210402488/commits/7d97b75/)
 
    - README.md created online with Bitbucket

    - สร้างไฟล์ README.md ผ่าน Bitbukket เพื่อให้มีไฟล์อยู่ใน Repository เพื่อทดสอบการ Pull ลงมาที่ Computer
                                                                           
## Repository

- **data**

    > โฟลเดอร์สำหรับการเก็บไฟล์ .csv ซึ่งเป็นไฟล์ฐานข้อมูลของโปรแกรม

    - accounts.csv
    
        > ไฟล์เก็บฐานข้อมูลของบัญชีผู้ใช้

    - mails.csv
   
        > ไฟล์เก็บฐานข้อมูลของจดหมาย เอกสาร และพัสดุ
   
    - rooms.csv
   
        > ไฟล์เก็บฐานข้อมูลของห้องพัก
 
- **image**
 
    > โฟลเดอร์สำหรับเก็บรูปภาพต่าง ๆ ที่ไฟล์ .csv ไม่สามารถเก็บได้ด้วยตัวเอง และรูปภาพบางอย่างเพื่อรองรับการใช้งานของโปรแกรม
   
    - **adminAvatar**
   
        > โฟลเดอร์สำหรับเก็บรูปภาพของบัญชี Administrator
   
    - **default**
   
        > โฟลเดอร์สำหรับเก็บรูปภาพเพื่อรองรับการใช้งานของโปรแกรม
   
    - **mailPicture**
   
        > โฟลเดอร์สำหรับเก็บรูปภาพของ Mail

    - **staffAvatar**

        > โฟลเดอร์สำหรับเก็บรูปภาพของบัญชี Officer

    - **userAvatar**

        > โฟลเดอร์สำหรับเก็บรูปภาพของบัญชี Resident

- PACKMAN_APPLICATION.zip

    > ไฟล์ .zip ของโปรแกรม PackMan

- **PACKMAN_APPLICATION**
     
    > โฟลเดอร์สำหรับเก็บไฟล์ของโปรแกรม เพื่อให้ผู้อื่นนำไปใช้

    - 6210402488-jar.jar
     
        > ไฟล์โปรแกรม PackMan
     
    - launcher.bat
    
        > ชุดคำสั่ง Run โปรแกรม (เมื่อไฟล์ 6210402488-jar.jar สั่งใช้งานไม่ได้)

- **src**
 
    - **main**
    
        - **java**
        
            - **packman**
            
                > Package หลักของ Project  
  
                - **controllers**
               
                    - **administratorMenu**
                    
                        - AdminChangePassController.java
                        
                            > Class ควบคุมไฟล .fxml สำหรับหน้าเปลี่ยน Password ของ Administrator 
                        
                        - AdministratorController.java
                        
                            > Class ควบคุมไฟล .fxml สำหรับหน้า Main Menu ของ Administrator 
                        
                        - OfficerRegisterController.java
                    
                            > Class ควบคุมไฟล .fxml สำหรับหน้า Register for Officer ของ Administrator 
                    
                    - **mainMenu**
                    
                        - DeveloperController.java
                        
                            > Class ควบคุมไฟล .fxml สำหรับหน้าข้อมูลนักพัฒนา
                        
                        - HelpController.java
                        
                            > Class ควบคุมไฟล .fxml สำหรับหน้าให้ข้อมูลเพิ่มเติมกับผู้ใช้งาน
                        
                        - LoginController.java
                        
                            > Class ควบคุมไฟล .fxml สำหรับหน้า Login
                        
                        - ResidentRegisterController.java
                        
                            > Class ควบคุมไฟล .fxml สำหรับหน้า Self Register ของ Resident
                            
                    - **officerMenu**
                    
                        - **mailRoomMenu**
                        
                            - AcceptRoomController.java
                          
                                > Class ควบคุมไฟล .fxml สำหรับหน้าดูพัสดุที่ถูกรับแล้วของ Officer
                          
                            - CreateMailController.java
                            
                                > Class ควบคุมไฟล .fxml สำหรับหน้าลงทะเบียนจดหมายใหม่ของ Officer
                            
                            - MailRoomController.java
                            
                                > Class ควบคุมไฟล .fxml สำหรับหน้าจัดการจดหมายของ Officer
                        
                        - CreateResidentController.java
                        
                            > Class ควบคุมไฟล .fxml สำหรับหน้า Register for Resident ของ Officer
                        
                        - CreateRoomController.java
                        
                            > Class ควบคุมไฟล .fxml สำหรับหน้ากรอกข้อมูลห้องพักใหม่
                        
                        - OfficerChangePassController.java
                        
                            > Class ควบคุมไฟล .fxml สำหรับหน้าเปลี่ยน Password ของ Officer
                        
                        - RoomManageController.java
                    
                            > Class ควบคุมไฟล .fxml สำหรับหน้าจัดการผู้พักอาศัย
                    
                    - **residentMenu**
                    
                        - ChangePasswordController.java
                        
                            > Class ควบคุมไฟล .fxml สำหรับหน้าเปลี่ยน Password ของ Resident
                        
                        - ResidentMailboxController.java
               
                            > Class ควบคุมไฟล .fxml สำหรับหน้าดูจดหมายของ Resident
               
                - **models**
               
                    - **accounts**
                    
                        - **subtypes**
                        
                            - AdministratorAccount.java
                            
                                > Class สำหรับบัญชี Administrator
                            
                            - OfficerAccount.java
                            
                                > Class สำหรับบัญชี Officer
                            
                            - ResidentAccount.java
                        
                                > Class สำหรับบัญชี Resident
                        
                        - Account.java
                        
                            > Super Class สำหรับบัญชีผู้ใช้งาน
                        
                        - AccountList.java
                    
                            > List สำหรับการทำงานกับ Account หลายบัญชี
                    
                    - **buildings**
                    
                        - **subtypes**
                        
                            - DuplexRoom.java
                            
                                > Class สำหรับห้องพักแบบผู้อยู่อาศัย 4 คน
                            
                            - StudioRoom.java
                            
                                > Class สำหรับห้องพักแบบผู้อยู่อาศัย 1 คน
                            
                            - SuiteRoom.java
                        
                                > Class สำหรับห้องพักแบบผู้อยู่อาศัย 2 คน
                        
                        - Room.java
                        
                            > Super Class สำหรับห้องพัก
                        
                        - RoomList.java
                    
                            > List สำหรับการทำงานกับ Room หลายบัญชี
                    
                    - **mails**
                    
                        - **details**
                        
                            - Size.java
                            
                                > Interface ของขนาด
                            
                            - Size2D.java
                            
                                > Class ของขนาดแบบ 2 มิติ
                                
                            - Size3D.java
                        
                                > Class ของขนาดแบบ 3 มิติ
                                                                       
                        - **subtypes**
                        
                            - Document.java
                            
                                > Class สำหรับเอกสาร
                            
                            - Letter.java
                            
                                > Class สำหรับจดหมาย
                            
                            - Parcel.java
                                
                                > Class สำหรับพัสดุ
                                
                        - Mail.java
                        
                            > Super Class สำหรับจดหมาย เอกสาร และพัสดุ
                        
                        - MailList.java
                        
                            > List สำหรับการทำงานกับ Mail หลายบัญชี
                        
                - **services**
               
                    - **accountDataBase**
                        
                        - AccountDataSource.java
                        
                            > Interface สำหรับการจัดการฐานข้อมูลของบัญชีผู้ใช้
                        
                        - AccountFileDataSource.java
                        
                            > Class สำหรับการจัดการฐานข้อมูลของบัญชีผู้ใช้ผ่านไฟล์ .csv
                        
                        - AccountHardcodeDataSource.java
                        
                            > Class สำหรับการจัดการฐานข้อมูลของบัญชีผู้ใช้ผ่านการกรอกฐานข้อมูลด้วยตนเอง
                        
                    - **mailDataBase**
                    
                        - MailDataSource.java
                        
                            > Interface สำหรับการจัดการฐานข้อมูลของจดหมาย เอกสาร และพัสดุ
                        
                        - MailFileDataSource.java
                        
                            > Class สำหรับการจัดการฐานข้อมูลของจดหมายผ่านไฟล์ .csv
                        
                        - MailHardcodeDataSource.java
                    
                            > Class สำหรับการจัดการฐานข้อมูลของจดหมายผ่านการกรอกฐานข้อมูลด้วยตนเอง
                    
                    - **roomDataBase**
                    
                        - RoomDataSource.java
                        
                            > Interface สำหรับการจัดการฐานข้อมูลของห้องพัก
                        
                        - RoomFileDataSource.java
                        
                            > Class สำหรับการจัดการฐานข้อมูลของห้องพักผ่านไฟล์ .csv
                        
                        - RoomHardcodeDataSource.java
                    
                            > Class สำหรับการจัดการฐานข้อมูลของห้องพักผ่านการกรอกฐานข้อมูลด้วยตนเอง
                                                                               
                    - StringConfiguration.java
               
                        > Helper Class สำหรับจัดการ String
               
                - Initial.java
               
                    > Main Class ของ Project
               
        - **resources**
        
            - **images**
            
                - **default**
            
                    - **kirbyTheme**
                    
                        > โฟลเดอร์สำหรับเก็บรูปที่ไว้ใช้กับ Theme Kirby
                    
                    - **spiderTheme**
                    
                        > โฟลเดอร์สำหรับเก็บรูปที่ไว้ใช้กับ Theme Spider Man
                    
                    - icon.png
                    
                        > รูปภาพ Icon ของโปรแกรม
                    
            - **stages**
            
                - **administratorMenu**
                
                    - admin_change_pass_stage.fxml
                    
                        > ไฟล์ GUI หน้าเปลี่ยน Password ของ Administrator 
                    
                    - administrator_stage.fxml
                    
                        > ไฟล์ GUI หน้า Main Menu ของ Administrator 
                    
                    - officer_register_stage.fxml
                
                        > ไฟล์ GUI หน้า Register for Officer ของ Administrator 
                
                - **mainMenu**
                
                    - developer_stage.fxml
                    
                        > ไฟล์ GUI หน้าข้อมูลนักพัฒนา
                    
                    - help_stage.fxml
                    
                        > ไฟล์ GUI หน้าให้ข้อมูลเพิ่มเติมกับผู้ใช้งาน
                    
                    - login_stage.fxml
                    
                        > ไฟล์ GUI หน้า Login
                    
                    - resident_register_stage.fxml
                
                        > ไฟล์ GUI หน้า Self Register ของ Resident
                
                - **officerMenu**
                
                    - **mailRoomMenu**
                    
                        - accept_room_stage.fxml
                        
                            > ไฟล์ GUI หน้าดูพัสดุที่ถูกรับแล้วของ Officer
                        
                        - create_mail_stage.fxml
                    
                            > ไฟล์ GUI หน้าลงทะเบียนจดหมายใหม่ของ Officer
                    
                        - mail_room_stage.fxml
                    
                            > ไฟล์ GUI หน้าจัดการจดหมายของ Officer 
                    
                    - create_resident_stage.fxml
                    
                        > ไฟล์ GUI หน้า Register for Resident ของ Officer
                    
                    - create_room_stage.fxml
                    
                        > ไฟล์ GUI หน้ากรอกข้อมูลห้องพักใหม่
                    
                    - officer_change_pass_stage.fxml
                    
                        > ไฟล์ GUI หน้าเปลี่ยน Password ของ Officer
                    
                    - room_manage_stage.fxml
                
                        > ไฟล์ GUI หน้าจัดการผู้พักอาศัย
                
                - **residentMenu**
                
                    - change_password_stage.fxml
                
                        > ไฟล์ GUI หน้าเปลี่ยน Password ของ Resident
                
                    - resident_mailbox_stage.fxml
                    
                        > ไฟล์ GUI หน้าดูจดหมายของ Resident
                    
            - **stylesheets**
            
                - darkTheme.css
                
                    > ไฟล์ .css สำหรับเปลี่ยนเป็น Theme Spider Man
                
                - lightTheme.css
                
                    > ไฟล์ .css สำหรับเปลี่ยนเป็น Theme Kirby

- 6210402488.pdf

    > คู่มือการใช้งานโปรแกรม PackMan
                
- .gitignore

- README.md

- pom.xml
    
## Required Fonts

- [Arial Rounded MT Bold](https://www.download-free-fonts.com/details/92774/arial-rounded-mt-bold/)
- [AngsanaUPC](https://fontzone.net/font-download/angsanaupc/)

## Unable to Run .JAR File (Windows 10)

##### Method 1: Using Command Prompt

Step 1: Click on the Start button on your desktop and type Command Prompt in the search field. Right-click on the result and select Run as administrator.

Step 2: In the Command Prompt window, type the below command in the below format and hit Enter.

    > cd <FILE_PATH>
    > java -jar 6210402488-jar.jar
    
##### Method 2: Using .bat File

Double-click on the launcher.bat and 6210402488-jar.jar will open smoothly.

## Suggestion

> ควรศึกษาวิธีการใช้งานอย่างละเอียดผ่านไฟล์ 6210402488.pdf ที่ผู้จัดทำได้เขียนเอาไว้

> สำหรับลูกบ้านที่ยังไม่มีบัญชีผู้ใช้ และทราบหมายเลขห้องพักของตนเองแล้ว สามารถกดปุ่ม Register เพื่อสร้างบัญชีผู้ใช้ของตนเองได้เลย

> สำหรับเจ้าหน้าที่ส่วนกลางที่ยังไม่มีบัญชีผู้ใช้ โปรดติดต่อกับผู้จัดการระบบ เพื่อให้ผู้ดูแลระบบดำเนินการสร้างบัญชีให้

> ไม่สามารถสร้างบัญชีผู้จัดการระบบได้ ต้องใช้บัญชีที่สร้างไว้แล้วเท่านั้น

## Account (Ready for use)

#### `Administrator Account `

    USERNAME : musk
    PASSWORD : musk

    USERNAME : evans
    PASSWORD : chris
    
#### `Officer Account `

    USERNAME : zuck
    PASSWORD : zuck
    
    USERNAME : cook
    PASSWORD : cook
    
#### `Resident Account`

    USERNAME : bill
    PASSWORD : bill
    
    USERNAME : jobs
    PASSWORD : jobs