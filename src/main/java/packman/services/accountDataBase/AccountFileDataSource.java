package packman.services.accountDataBase;

import packman.models.account.Account;
import packman.models.account.AccountList;
import packman.models.account.accountSubtype.AdministerAccount;
import packman.models.account.accountSubtype.OfficerAccount;
import packman.models.account.accountSubtype.ResidentAccount;
import packman.models.account.details.AccountType;
import packman.models.account.details.Name;
import packman.models.account.details.Picture;

import java.io.*;
import java.time.LocalDateTime;

public class AccountFileDataSource implements AccountDataSource {

    private String fileDirectoryName;
    private String fileName;
    private AccountList accounts;

    public AccountFileDataSource(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
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

    private void readData() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            /* Resident Account */
            if (data[0].trim().equals("0")) {
                Account account = new ResidentAccount(new Picture(data[1].trim()), LocalDateTime.of(Integer.parseInt(data[2].trim()), Integer.parseInt(data[3].trim()), Integer.parseInt(data[4].trim()), Integer.parseInt(data[5].trim()), Integer.parseInt(data[6].trim()), Integer.parseInt(data[7].trim())), data[8].trim(), data[9].trim(), new Name(data[10].trim(), data[11].trim()), data[12].trim());
                accounts.add(account);
            }
            /* Officer Account */
            else if (data[0].trim().equals("1") && data[12].trim().equals("0")) {
                Account account = new OfficerAccount(new Picture(data[1].trim()), LocalDateTime.of(Integer.parseInt(data[2].trim()), Integer.parseInt(data[3].trim()), Integer.parseInt(data[4].trim()), Integer.parseInt(data[5].trim()), Integer.parseInt(data[6].trim()), Integer.parseInt(data[7].trim())), data[8].trim(), data[9].trim(), new Name(data[10].trim(), data[11].trim()), false, Integer.parseInt(data[13].trim()));
                accounts.add(account);
            } else if (data[0].trim().equals("1") && data[12].trim().equals("1")) {
                Account account = new OfficerAccount(new Picture(data[1].trim()), LocalDateTime.of(Integer.parseInt(data[2].trim()), Integer.parseInt(data[3].trim()), Integer.parseInt(data[4].trim()), Integer.parseInt(data[5].trim()), Integer.parseInt(data[6].trim()), Integer.parseInt(data[7].trim())), data[8].trim(), data[9].trim(), new Name(data[10].trim(), data[11].trim()), true, Integer.parseInt(data[13].trim()));
                accounts.add(account);
            }
            /* Administer Account */
            else if (data[0].trim().equals("2")) {
                Account account = new AdministerAccount(new Picture(data[1].trim()), LocalDateTime.of(Integer.parseInt(data[2].trim()), Integer.parseInt(data[3].trim()), Integer.parseInt(data[4].trim()), Integer.parseInt(data[5].trim()), Integer.parseInt(data[6].trim()), Integer.parseInt(data[7].trim())), data[8].trim(), data[9].trim(), new Name(data[10].trim(), data[11].trim()));
                accounts.add(account);
            }
        }
        reader.close();
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

    @Override
    public void setAccountsData(AccountList accounts) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Account account: accounts.toList()) {
                String line = new String();
                if (account.getAccountType() == AccountType.RESIDENT) {
                    line = "0" + "," + account.getImagePath() + "," + account.getLastLogin().getYear() + "," + account.getLastLogin().getMonthValue() + "," + account.getLastLogin().getDayOfMonth() + "," + account.getLastLogin().getHour() + "," + account.getLastLogin().getMinute() + "," + account.getLastLogin().getSecond() + "," + account.getUsername() + "," + account.getPassword() + "," + account.getFirstName() + "," + account.getLastName() + "," + ((ResidentAccount)account).getRoomNumber();
                }
                else if (account.getAccountType() == AccountType.OFFICER && !((OfficerAccount)account).isBanned()) {
                    line = "1" + "," + account.getImagePath() + "," + account.getLastLogin().getYear() + "," + account.getLastLogin().getMonthValue() + "," + account.getLastLogin().getDayOfMonth() + "," + account.getLastLogin().getHour() + "," + account.getLastLogin().getMinute() + "," + account.getLastLogin().getSecond() + "," + account.getUsername() + "," + account.getPassword() + "," + account.getFirstName() + "," + account.getLastName() + "," + 0 + "," + ((OfficerAccount)account).getBannedLoginCount();
                } else if (account.getAccountType() == AccountType.OFFICER && ((OfficerAccount)account).isBanned()) {
                    line = "1" + "," + account.getImagePath() + "," + account.getLastLogin().getYear() + "," + account.getLastLogin().getMonthValue() + "," + account.getLastLogin().getDayOfMonth() + "," + account.getLastLogin().getHour() + "," + account.getLastLogin().getMinute() + "," + account.getLastLogin().getSecond() + "," + account.getUsername() + "," + account.getPassword() + "," + account.getFirstName() + "," + account.getLastName() + "," + 1 + "," + ((OfficerAccount)account).getBannedLoginCount();
                }
                else if (account.getAccountType() == AccountType.ADMINISTER) {
                    line = "2" + "," + account.getImagePath() + "," + account.getLastLogin().getYear() + "," + account.getLastLogin().getMonthValue() + "," + account.getLastLogin().getDayOfMonth() + "," + account.getLastLogin().getHour() + "," + account.getLastLogin().getMinute() + "," + account.getLastLogin().getSecond() + "," + account.getUsername() + "," + account.getPassword() + "," + account.getFirstName() + "," + account.getLastName();
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
