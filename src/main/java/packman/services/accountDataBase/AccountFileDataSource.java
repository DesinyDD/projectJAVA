package packman.services.accountDataBase;

import packman.models.accounts.Account;
import packman.models.accounts.AccountList;
import packman.models.accounts.subtypes.AdministratorAccount;
import packman.models.accounts.subtypes.OfficerAccount;
import packman.models.accounts.subtypes.ResidentAccount;

import java.io.*;
import java.time.LocalDateTime;

public class AccountFileDataSource implements AccountDataSource {
    private String fileDirectoryName;
    private String fileName;
    private AccountList accounts;

    public AccountFileDataSource(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName          = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }

    @Override
    public AccountList getAccountsData() {
        try {
            accounts = new AccountList();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return accounts;
    }

    private void readData() throws IOException {
        String filePath       = fileDirectoryName + File.separator + fileName;
        File file             = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line           = "";

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            String username  = data[1].trim();
            String password  = data[2].trim();
            String firstName = data[3].trim();
            String lastName  = data[4].trim();
            String picPath   = data[5].trim();
            int year   = Integer.parseInt(data[6].trim());
            int month  = Integer.parseInt(data[7].trim());
            int day    = Integer.parseInt(data[8].trim());
            int hour   = Integer.parseInt(data[9].trim());
            int minute = Integer.parseInt(data[10].trim());
            int second = Integer.parseInt(data[11].trim());
            LocalDateTime lastLogin = LocalDateTime.of(year, month, day, hour, minute, second);

            /* Resident Account */
            if (data[0].trim().equals("0")) {
                boolean activate = Boolean.parseBoolean(data[12].trim());
                String roomID    = data[13].trim();
                int yearEnter    = Integer.parseInt(data[14].trim());
                int monthEnter   = Integer.parseInt(data[15].trim());
                int dayEnter     = Integer.parseInt(data[16].trim());
                int hourEnter    = Integer.parseInt(data[17].trim());
                int minuteEnter  = Integer.parseInt(data[18].trim());
                int secondEnter  = Integer.parseInt(data[19].trim());
                LocalDateTime enterDate = LocalDateTime.of(yearEnter, monthEnter, dayEnter, hourEnter, minuteEnter, secondEnter);
                Account account = new ResidentAccount(username, password, firstName, lastName, picPath, lastLogin, activate, roomID, enterDate);
                accounts.add(account);
            }

            /* Officer Account */
            else if (data[0].trim().equals("1")) {
                boolean banned = Boolean.parseBoolean(data[12].trim());
                int bannedLoginCount = Integer.parseInt(data[13].trim());
                Account account = new OfficerAccount(username, password, firstName, lastName, picPath, lastLogin, banned, bannedLoginCount);
                accounts.add(account);
            }

            /* Administer Account */
            else if (data[0].trim().equals("2")) {
                Account account = new AdministratorAccount(username, password, firstName, lastName, picPath, lastLogin);
                accounts.add(account);
            }
        }
        reader.close();
    }

    @Override
    public void setAccountsData(AccountList accounts) {
        String filePath       = fileDirectoryName + File.separator + fileName;
        File file             = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Account account: accounts.toArrayList()) {
                String line = new String();
                if (account.isResident()) {
                    ResidentAccount resident = account.toResident();
                    line = "0" + "," +
                            resident.getUsername() + "," +
                            resident.getPassword() + "," +
                            resident.getFirstName() + "," +
                            resident.getLastName() + "," +
                            resident.getPicPath() + "," +
                            resident.getLastLogin().getYear() + "," +
                            resident.getLastLogin().getMonthValue() + "," +
                            resident.getLastLogin().getDayOfMonth() + "," +
                            resident.getLastLogin().getHour() + "," +
                            resident.getLastLogin().getMinute() + "," +
                            resident.getLastLogin().getSecond() + "," +
                            resident.isActivate() + "," +
                            resident.getRoomID() + "," +
                            resident.getEnterDate().getYear() + "," +
                            resident.getEnterDate().getMonthValue() + "," +
                            resident.getEnterDate().getDayOfMonth() + "," +
                            resident.getEnterDate().getHour() + "," +
                            resident.getEnterDate().getMinute() + "," +
                            resident.getEnterDate().getSecond();
                } else if (account.isOfficer()) {
                    OfficerAccount officer = account.toOfficer();
                    line = "1" + "," +
                            officer.getUsername() + "," +
                            officer.getPassword() + "," +
                            officer.getFirstName() + "," +
                            officer.getLastName() + "," +
                            officer.getPicPath() + "," +
                            officer.getLastLogin().getYear() + "," +
                            officer.getLastLogin().getMonthValue() + "," +
                            officer.getLastLogin().getDayOfMonth() + "," +
                            officer.getLastLogin().getHour() + "," +
                            officer.getLastLogin().getMinute() + "," +
                            officer.getLastLogin().getSecond() + "," +
                            officer.isBanned() + "," +
                            officer.getBannedLoginCount();
                } else if (account.isAdministrator()) {
                    AdministratorAccount admin = account.toAdministrator();
                    line = "2" + "," +
                            admin.getUsername() + "," +
                            admin.getPassword() + "," +
                            admin.getFirstName() + "," +
                            admin.getLastName() + "," +
                            admin.getPicPath() + "," +
                            admin.getLastLogin().getYear() + "," +
                            admin.getLastLogin().getMonthValue() + "," +
                            admin.getLastLogin().getDayOfMonth() + "," +
                            admin.getLastLogin().getHour() + "," +
                            admin.getLastLogin().getMinute() + "," +
                            admin.getLastLogin().getSecond();
                }
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
