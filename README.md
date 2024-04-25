# Aim of this application

Crafted for Android, this application streamlines your expense management process. 
It also gathers annotated receipt images, a valuable resource for training a machine learning model.
This model will be adept at extracting and capitalizing on the information within these receipts. 
In the future, users would gain access to a beneficial feature: 
the ability to automatically log an entry into the table by simply capturing a receipt image with the trained model.

App is made up with the following elements (Details on them are provided in the following sections):
- Table
- Panel
- Estimation/Scheduling

And provides the following features:
- Enable user to predict their future financial situation.
- Enable user to register a record into the table by their hands.
- Enable user to edit a record in the table.
- Enable user to manage categories.
- Enable user to manage currencies. (e.g. exchange rate)
- Enable user to manage the estimation/schedule with highly customizable Logic Editor.
- Enable user to manage the labels with highly customizable Logic Editor.
- Enable user to configure the confirmation panels.
- Register a record into the table automatically based on the estimation.
- Register a record into the table automatically based on the schedule.
- Provide a panel to confirm the automatically registered records.
- Provide a panel to label the result of the estimations.
- (HOPEFULLY) Register a record into the table automatically by taking a picture of the receipt.
- (HOPEFULLY) Register a record into the table automatically by connecting to the external services (e.g. bank account) 
- (HOPEFULLY) Automatically set the exchange rates between currencies.

## Table

Table is a list of records. Each record contains the following fields:
- Date
- Name of the item a user purchased
- Amount of the item' price
- Currency of the price
- Categories of the item
- if the picture of the item is uploaded or not (not visible to the user)

### Categories
Each records can have multiple categories.
Thanks to it, Category is so powerful that it can be used for the following purposes:

#### Users can use categories to simulate having multiple wallets,
such as "Wallet" and "MyBankAccount".
This means that users have the ability to record transactions between different payment methods. 
For example, if a user transfers money from their bank account to a digital wallet like PayPay, 
or from cash to a bank account, they can log these transactions in the application.

#### Users can analyze their spending habits by category.
For instance, users have the ability to track their spending on various categories such as food, transportation, and entertainment individually. 
The system allows for easy categorization, enabling users to further break down expenses within a category. 
For example, food expenses can be subdivided into groceries, restaurant meals, and snacks. 


App provide with some predefined categories including food, transportation, and entertainment.

## Panel

Panel is classified into two types:
- Label
- Confirmation

### Label

The Label panel is designed to display the results of financial estimations or the current state of a user's finances. 
Its content can be extensively customized using the Logic Editor found on the PanelSetting-page. 
When users need to access the results of their estimations, they can utilize the GET-method provided by the Logic Editor. 
More details on this process are described below.

### Confirmation

Confirmation panel is to confirm some of automatically registered records and scheduled expenses.
what kind of records needs to be confirmed is configured in PanelSetting-page.

## Estimation/Scheduling

Estimation and Scheduling can be configured in the Estimation-page.
Estimation is to predict the future financial situation based on the past records.
Scheduling is to register a record into the table automatically based on the schedule.
Both are configured with the Logic Editor.

## Logic Editor

Logic Editor is a tool to configure the Label Panel and Estimation/Scheduling.
Logic is defined by connecting nodes.

Each node is one of the following types:
- GET: gets dataframe from the table based on query. This node handles the prediction as a real data.
  if a dataframe is passed, this exploits that with conditions.
- SIZE: returns size of a dataframe.
- MODE/MEAN/MIN/LQ/MID/UQ/MAX: take a statistic value of price field of a dataframe.
- LIM: exploits a dataframe up to the specified number of records.
- ADD/SUB/MUL/DIV: operates two numbers.
- NUM: returns a number.
- TEXT: returns a text.
- OR/ELEM/DIFF/AND: operates two dataframes like set operation.
- LABEL: sets a label text of label panel.
- HEAD: sets a header text of label panel.
- EXC: exchange currency of dataframe.

# Implementation

this project adopts the layered architecture. The following layers are defined:
- UI
  - View: Defines the layout of the UI components. Except to implement scene transitions, no event listeners are defined here.
  - Theme: Defines the color, font, and shape of the UI components.
  - ViewModel: Holds/Requires data for View. Except to implement scene transitions, All event listeners for View are defined here.
- DOMAIN: Defines all objects found in the application. 
- REPOSITORY: Defines the methods to access the data in a way that is useful for the DOMAIN layer.
- INFRASTRUCTURE: Actually manage data. It provides access to the database, file system and network.

Instances of singleton objects will be created in MainActivity-file.

## UI.View/UI.ViewModel

The View is composed with the following components:
- Container
  - PageHolder
  - PopUp
  - PanelList
- Pages
  - PanelSetting
  - PanelDisplay
  - Estimation
  - Table
  - ApplicationSetting
- Panel
- Section

MainActivity holds PageHolder and PopUp directly.

PageHolder holds the Pages. PanelDisplay-page, PanelSetting-page, Estimation hold PanelList. PanelList holds Panels.
Confirmation and Label Panel are defined in two way: for Display and for Setting.
Estimation Panel are defined only for Estimation Page. 

PopUp holds Sections.

## REPOSITORY.LogicData

LogicData is saved as a json file.


