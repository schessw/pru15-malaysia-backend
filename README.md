# pru15-malaysia-backend

API developed for Malaysia's 15th General Election Result, using Java Spring Boot.

## Application Properties
Replace the user and password with your own credentials.

    spring.datasource.url=jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=TESTDB;encrypt=true;trustServerCertificate=true;  
    spring.datasource.username=User1  
    spring.datasource.password=User1  
    spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver  
    spring.jpa.show-sql=true  
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect  
    spring.jpa.hibernate.ddl-auto = create  
    logging.file.path=logs

## Postman Collection
Postman Collection for this API can be downloaded from: https://drive.google.com/file/d/1GB4R5er_VEElzZMTdnI_d6MxfGShfqCJ/view?usp=sharing

## Logging

    The log file generated during runtime can be found in './logs/spring.log'

## Note

=> Pagination is implemented for **localhost:8080/api/v1/party/:id/candidate?page=0&size=10**

=> An API calling from 3rd Party API is implemented for **localhost:8080/api/v1/state/external**

## Database Design
![Database Design](https://i.imgur.com/pTNqvCR.png)

## Sample data
Run the following SQL statements to load sample data into your database.

1.) Load data into [state]

    INSERT [dbo].[state] ([state_name]) VALUES (N'Perlis');
    INSERT [dbo].[state] ([state_name]) VALUES (N'Kedah');
    INSERT [dbo].[state] ([state_name]) VALUES (N'Kelantan');
    INSERT [dbo].[state] ([state_name]) VALUES (N'Terengganu');
    INSERT [dbo].[state] ([state_name]) VALUES (N'Pulau Pinang');
    INSERT [dbo].[state] ([state_name]) VALUES (N'Perak');
    INSERT [dbo].[state] ([state_name]) VALUES (N'Pahang');
    INSERT [dbo].[state] ([state_name]) VALUES (N'Selangor');
    INSERT [dbo].[state] ([state_name]) VALUES ( N'W.P Kuala Lumpur');
    INSERT [dbo].[state] ([state_name]) VALUES (N'W.P Putrajaya');
    INSERT [dbo].[state] ([state_name]) VALUES (N'Negeri Sembilan');
    INSERT [dbo].[state] ([state_name]) VALUES (N'Melaka');
    INSERT [dbo].[state] ([state_name]) VALUES (N'Johor');
    INSERT [dbo].[state] ([state_name]) VALUES (N'W.P Labuan');
    INSERT [dbo].[state] ([state_name]) VALUES (N'Sabah');
    INSERT [dbo].[state] ([state_name]) VALUES (N'Sarawak');

2.) Load data into [parliament]

    INSERT [dbo].[parliament] ([parliament_code], [parliament_name], [state_id]) VALUES (N'P.001', N'Padang Besar', 1);
    INSERT [dbo].[parliament] ([parliament_code], [parliament_name], [state_id]) VALUES (N'P.002', N'Kangar', 1);
    INSERT [dbo].[parliament] ([parliament_code], [parliament_name], [state_id]) VALUES (N'P.003', N'Arau', 1);
    INSERT [dbo].[parliament] ([parliament_code], [parliament_name], [state_id]) VALUES (N'P.004', N'Langkawi', 2);
    INSERT [dbo].[parliament] ([parliament_code], [parliament_name], [state_id]) VALUES (N'P.005', N'Jerlun', 2);
    INSERT [dbo].[parliament] ([parliament_code], [parliament_name], [state_id]) VALUES (N'P.006', N'Kubang Pasu', 2);
    INSERT [dbo].[parliament] ([parliament_code], [parliament_name], [state_id]) VALUES (N'P.007', N'Padang Terap', 2);
    INSERT [dbo].[parliament] ([parliament_code], [parliament_name], [state_id]) VALUES (N'P.008', N'Pokok Sena', 2);
    INSERT [dbo].[parliament] ([parliament_code], [parliament_name], [state_id]) VALUES (N'P.009', N'Alor Setar', 2);
    INSERT [dbo].[parliament] ([parliament_code], [parliament_name], [state_id]) VALUES (N'P.010', N'Kuala Kedah', 2);
    INSERT [dbo].[parliament] ([parliament_code], [parliament_name], [state_id]) VALUES (N'P.011', N'Pendang', 2);

3.) Load data into [party]

    INSERT [dbo].[party] ([party_name]) VALUES (N'BARISAN NASIONAL (BN)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PAKATAN HARAPAN (PH)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PERIKATAN NASIONAL (PN)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'GABUNGAN PARTI SARAWAK (GPS)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'IKATAN DEMOKRATIK MALAYSIA (MUDA)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI BANGSA MALAYSIA (PBM)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI BANSA DAYAK SARAWAK (PBDS)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI BERSATU RAKYAT SABAH (PBRS)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI BUMI KENYALANG (PBK)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI BUMIPUTERA PERKASA MALAYSIA (PUTRA)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI GABUNGAN RAKYAT SABAH (GRS)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI ISLAM SE MALAYSIA (PAS)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI KESEJAHTERAAN DEMOKRATIK MASYARAKAT (KDM)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI PEJUANG TANAHAIR (PEJUANG)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI PERPADUAN RAKYAT SABAH (PPRS)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI RAKYAT MALAYSIA (PRM)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI SARAWAK BERSATU (PSB)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI SEDAR RAKYAT SARAWAK (SEDAR)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI SOSIALIS MALAYSIA (PSM)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI TINDAKAN DEMOKRATIK (DAP)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI UTAMA RAKYAT (PUR)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'PARTI WARISAN SABAH (WARISAN)');
    INSERT [dbo].[party] ([party_name]) VALUES (N'BEBAS (BEBAS)');

4.) Load data into [candidate]

    INSERT [dbo].[candidate] ([candidate_full_name], [candidate_display_name], [candidate_vote], [candidate_status], [party_id], [parliament_id]) VALUES (N'Rushdan Bin Rusmi', N'RUSHDAN BIN RUSMI', 24267, N'win', 3, 1);
    INSERT [dbo].[candidate] ([candidate_full_name], [candidate_display_name], [candidate_vote], [candidate_status], [party_id], [parliament_id]) VALUES (N'Zahida Binti Zarik Khan', N'ZAHIDA BINTI ZARIK KHAN', 11753, N'lose', 1, 1);
    INSERT [dbo].[candidate] ([candidate_full_name], [candidate_display_name], [candidate_vote], [candidate_status], [party_id], [parliament_id]) VALUES (N'Mohamad Bin Saad @ Yahaya', N'KAPT (B) HJ MOHAMAD YAHAYA', 7085, N'lose', 2, 1);
    INSERT [dbo].[candidate] ([candidate_full_name], [candidate_display_name], [candidate_vote], [candidate_status], [party_id], [parliament_id]) VALUES (N'Zahidi Bin Zainul Abidin', N'ZAHIDI BIN ZAINUL ABIDIN', 1939, N'lose', 23, 1);
    INSERT [dbo].[candidate] ([candidate_full_name], [candidate_display_name], [candidate_vote], [candidate_status], [party_id], [parliament_id]) VALUES (N'Ko Chu Liang', N'KO CHU LIANG', 244, N'lose', 22, 1);
    INSERT [dbo].[candidate] ([candidate_full_name], [candidate_display_name], [candidate_vote], [candidate_status], [party_id], [parliament_id]) VALUES (N'Noor Amin Bin Ahmad', N'AMIN AHMAD', 15143, N'lose', 2, 2);
    INSERT [dbo].[candidate] ([candidate_full_name], [candidate_display_name], [candidate_vote], [candidate_status], [party_id], [parliament_id]) VALUES (N'Fathin Amelina Binti Fazlie', N'FATHIN AMELINA', 7089, N'lose', 2, 3);
    INSERT [dbo].[candidate] ([candidate_full_name], [candidate_display_name], [candidate_vote], [candidate_status], [party_id], [parliament_id]) VALUES (N'Zabidi Bin Yahya', N'ZABIDI YAHYA', 5417, N'lose', 2, 4);
    INSERT [dbo].[candidate] ([candidate_full_name], [candidate_display_name], [candidate_vote], [candidate_status], [party_id], [parliament_id]) VALUES (N'Mohamed Fadzil Bin Mohd Ali', N'DR. MOHAMED FADZIL MOHD ALI', 6419, N'lose', 2, 5);
    INSERT [dbo].[candidate] ([candidate_full_name], [candidate_display_name], [candidate_vote], [candidate_status], [party_id], [parliament_id]) VALUES (N'Mohd Aizuddin Bin Ariffin', N'AIZUDDIN ARIFFIN', 16000, N'lose', 2, 6);
    INSERT [dbo].[candidate] ([candidate_full_name], [candidate_display_name], [candidate_vote], [candidate_status], [party_id], [parliament_id]) VALUES (N'Muaz Bin Abdullah', N'MUAZ ABDULLAH', 2702, N'lose', 2, 7);
    INSERT [dbo].[candidate] ([candidate_full_name], [candidate_display_name], [candidate_vote], [candidate_status], [party_id], [parliament_id]) VALUES (N'Mahfuz Bin Omar', N'DATO WIRA HJ MAHFUZ OMAR', 20524, N'lose', 2, 8);
    INSERT [dbo].[candidate] ([candidate_full_name], [candidate_display_name], [candidate_vote], [candidate_status], [party_id], [parliament_id]) VALUES (N'Ooi Tze Min', N'SIMON OOI TZE MIN', 27555, N'lose', 2, 9);
    INSERT [dbo].[candidate] ([candidate_full_name], [candidate_display_name], [candidate_vote], [candidate_status], [party_id], [parliament_id]) VALUES (N'Azman Bin Ismail', N'DR AZMAN ISMAIL', 28237, N'lose', 2, 10);
    INSERT [dbo].[candidate] ([candidate_full_name], [candidate_display_name], [candidate_vote], [candidate_status], [party_id], [parliament_id]) VALUES (N'Zulkifly Bin Mohamad', N'AMIN AHMAD', 8058, N'lose', 2, 11);

