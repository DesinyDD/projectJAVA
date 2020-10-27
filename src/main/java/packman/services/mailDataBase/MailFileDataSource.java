package packman.services.mailDataBase;

import packman.models.mails.Mail;
import packman.models.mails.MailList;
import packman.models.mails.details.Size2D;
import packman.models.mails.details.Size3D;
import packman.models.mails.subtypes.Document;
import packman.models.mails.subtypes.Letter;
import packman.models.mails.subtypes.Parcel;

import java.io.*;
import java.time.LocalDateTime;

public class MailFileDataSource implements MailDataSource {
    private String fileDirectoryName;
    private String fileName;
    private MailList mails;

    public MailFileDataSource(String fileDirectoryName, String fileName) {
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
    public MailList getMailsData() {
        try {
            mails = new MailList();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return mails;
    }

    private void readData() throws IOException {
        String filePath       = fileDirectoryName + File.separator + fileName;
        File file             = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line           = "";

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            String receiverFirstName = data[1].trim();
            String receiverLastName = data[2].trim();
            String receiverRoomID = data[3].trim();
            String senderFirstName = data[4].trim();
            String senderLastName = data[5].trim();
            String picPath = data[6].trim();

            String receivingOfficerUsername = data[7].trim();
            int receivingYear = Integer.parseInt(data[8].trim());
            int receivingMonth = Integer.parseInt(data[9].trim());
            int receivingDay = Integer.parseInt(data[10].trim());
            int receivingHour = Integer.parseInt(data[11].trim());
            int receivingMinute = Integer.parseInt(data[12].trim());
            int receivingSecond = Integer.parseInt(data[13].trim());
            LocalDateTime receivingTime = LocalDateTime.of(
                    receivingYear,
                    receivingMonth,
                    receivingDay,
                    receivingHour,
                    receivingMinute,
                    receivingSecond
            );

            String sendingOfficerUsername = data[14].trim();
            int sendingYear = Integer.parseInt(data[15].trim());
            int sendingMonth = Integer.parseInt(data[16].trim());
            int sendingDay = Integer.parseInt(data[17].trim());
            int sendingHour = Integer.parseInt(data[18].trim());
            int sendingMinute = Integer.parseInt(data[19].trim());
            int sendingSecond = Integer.parseInt(data[20].trim());
            LocalDateTime sendingTime = LocalDateTime.of(
                    sendingYear,
                    sendingMonth,
                    sendingDay,
                    sendingHour,
                    sendingMinute,
                    sendingSecond
            );

            double length = Double.parseDouble(data[21].trim());
            double width = Double.parseDouble(data[22].trim());

            /* Letter */
            if (data[0].trim().equals("1")) {
                Mail mail = new Letter(
                        receiverFirstName,
                        receiverLastName,
                        receiverRoomID,
                        senderFirstName,
                        senderLastName,
                        picPath,
                        receivingOfficerUsername,
                        receivingTime,
                        sendingOfficerUsername,
                        sendingTime,
                        new Size2D(length, width)
                );
                mails.add(mail);
            }

            /* Document */
            else if (data[0].trim().equals("2")) {
                String documentType = data[23].trim();
                Mail mail = new Document(
                        receiverFirstName,
                        receiverLastName,
                        receiverRoomID,
                        senderFirstName,
                        senderLastName,
                        picPath,
                        receivingOfficerUsername,
                        receivingTime,
                        sendingOfficerUsername,
                        sendingTime,
                        new Size2D(length, width),
                        documentType
                );
                mails.add(mail);
            }

            /* Parcel */
            else if (data[0].trim().equals("3")) {
                double height = Double.parseDouble(data[23].trim());
                String courierBrand = data[24].trim();
                String trackingNumber = data[25].trim();
                Mail mail = new Parcel(
                        receiverFirstName,
                        receiverLastName,
                        receiverRoomID,
                        senderFirstName,
                        senderLastName,
                        picPath,
                        receivingOfficerUsername,
                        receivingTime,
                        sendingOfficerUsername,
                        sendingTime,
                        new Size3D(length, width, height),
                        courierBrand,
                        trackingNumber
                );
                mails.add(mail);
            }
        }
        reader.close();
    }

    @Override
    public void setMailsData(MailList mails) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Mail mail: mails.toArrayList()) {
                String line = new String();

                /* Letter */
                if (mail.isLetter()) {
                    Letter letter = mail.toLetter();
                    line = "1" + "," +
                            letter.getReceiverFirstName() + "," +
                            letter.getReceiverLastName() + "," +
                            letter.getReceiverRoomID() + "," +
                            letter.getSenderFirstName() + "," +
                            letter.getSenderLastName() + "," +
                            letter.getPicPath() + "," +
                            letter.getReceivingOfficerUsername() + "," +
                            letter.getReceivingTime().getYear() + "," +
                            letter.getReceivingTime().getMonthValue() + "," +
                            letter.getReceivingTime().getDayOfMonth() + "," +
                            letter.getReceivingTime().getHour() + "," +
                            letter.getReceivingTime().getMinute() + "," +
                            letter.getReceivingTime().getSecond() + "," +
                            letter.getSendingOfficerUsername() + "," +
                            letter.getSendingTime().getYear() + "," +
                            letter.getSendingTime().getMonthValue() + "," +
                            letter.getSendingTime().getDayOfMonth() + "," +
                            letter.getSendingTime().getHour() + "," +
                            letter.getSendingTime().getMinute() + "," +
                            letter.getSendingTime().getSecond() + "," +
                            letter.getSize().getLength() + "," +
                            letter.getSize().getWidth();
                }

                /* Document */
                else if (mail.isDocument()) {
                    Document document = mail.toDocument();
                    line = "2" + "," +
                            document.getReceiverFirstName() + "," +
                            document.getReceiverLastName() + "," +
                            document.getReceiverRoomID() + "," +
                            document.getSenderFirstName() + "," +
                            document.getSenderLastName() + "," +
                            document.getPicPath() + "," +
                            document.getReceivingOfficerUsername() + "," +
                            document.getReceivingTime().getYear() + "," +
                            document.getReceivingTime().getMonthValue() + "," +
                            document.getReceivingTime().getDayOfMonth() + "," +
                            document.getReceivingTime().getHour() + "," +
                            document.getReceivingTime().getMinute() + "," +
                            document.getReceivingTime().getSecond() + "," +
                            document.getSendingOfficerUsername() + "," +
                            document.getSendingTime().getYear() + "," +
                            document.getSendingTime().getMonthValue() + "," +
                            document.getSendingTime().getDayOfMonth() + "," +
                            document.getSendingTime().getHour() + "," +
                            document.getSendingTime().getMinute() + "," +
                            document.getSendingTime().getSecond() + "," +
                            document.getSize().getLength() + "," +
                            document.getSize().getWidth() + "," +
                            document.getDocumentType();
                    }

                /* Parcel */
                else if (mail.isParcel()) {
                    Parcel parcel = mail.toParcel();
                    line = "3" + "," +
                            parcel.getReceiverFirstName() + "," +
                            parcel.getReceiverLastName() + "," +
                            parcel.getReceiverRoomID() + "," +
                            parcel.getSenderFirstName() + "," +
                            parcel.getSenderLastName() + "," +
                            parcel.getPicPath() + "," +
                            parcel.getReceivingOfficerUsername() + "," +
                            parcel.getReceivingTime().getYear() + "," +
                            parcel.getReceivingTime().getMonthValue() + "," +
                            parcel.getReceivingTime().getDayOfMonth() + "," +
                            parcel.getReceivingTime().getHour() + "," +
                            parcel.getReceivingTime().getMinute() + "," +
                            parcel.getReceivingTime().getSecond() + "," +
                            parcel.getSendingOfficerUsername() + "," +
                            parcel.getSendingTime().getYear() + "," +
                            parcel.getSendingTime().getMonthValue() + "," +
                            parcel.getSendingTime().getDayOfMonth() + "," +
                            parcel.getSendingTime().getHour() + "," +
                            parcel.getSendingTime().getMinute() + "," +
                            parcel.getSendingTime().getSecond() + "," +
                            parcel.getSize().getLength() + "," +
                            parcel.getSize().getWidth() + "," +
                            parcel.getSize().getHeight() + "," +
                            parcel.getCourierBrand() + "," +
                            parcel.getTrackingNumber();
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
