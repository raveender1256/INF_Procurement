package uiObjInfo;

public class UILocators {

	//common locators for login and home page navigation
	// Login page
	public String username_id = "id=userid";
	public String password_id = "id=password";
	public String signin_id = "id=btnActive";
	// Home page
	public String home_id = "id=pt1:_UIShome::icon";
	//Procurement Module specific Locators**** Added by Ryalaka

	//Purchase Requisitions Icon
	//Procurement Icon
	public String ProcurementIcon_xp = "//div[@title='Procurement']";			
	//Procurementexp_xp		
	public String RequisitionsIcon_xp = "//div[@title='Purchase Requisitions']";
	public String MyReceiptsIcon_xp = "//a[@title='My Receipts']";
	public String GoodsRequisitionLink_xp    = "//a[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:0:SP4:r7:0:i2:0:cl2']";
	public String ServicesRequisitionLink_xp = "//a[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:0:SP4:r7:0:i2:1:cl2']";	
	// select Request Type
	public String RequisitionType_xp= "//select[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:SmartFormId::content']";
	// Item Description test box	
	public String ItemDescription_xp= "//textarea[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:ItemI1:0:inputText3::content']";	
	// Category Name
	public String CategoryName_xp ="//input[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:ItemI1:0:categoryNameId::content']";	
	// Quantity
	public String Quantity_xp= "//input[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:ItemI1:0:quantity::content']";
	// UOM name
	public String UOMName_xp ="//input[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:ItemI1:0:unitOfMeasurePrimaryId::content']";
	// Price
	public String Price_xp = "//input[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:ItemI1:0:currUnitPriceItemRN::content']";	
	// Currency	
	public String Currency_xp ="//input[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:ItemI1:0:currencyCodeId::content']";
	//Project
	public String Project_xp="//input[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:ItemI1:0:df1_RequisitionLineDFFIteratorproject_Display__FLEX_EMPTY::content']";
	// Supplier
	public String Supplier_xp ="//input[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:ItemI1:0:vendorNameId::content']";
	// AddtoCart Button
	public String AddtoCartButton_xp="//div[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:ctb2']//a[@class='xx3']";
	//click on cart 
	public String OpenCart_xp ="//img[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s6:i1']";
	//click on Reveiw button
	public String  ReviewButton_xp="//button[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s6:cb2']";	
	//Save and close Dropdown and button
	public String  	SaveandCloseDD_xp = "//a[@class='x1qd']";		
	public String  	SaveDD_xp = "//a[contains(@id,'APsv2::popEl')]";			
	public String  	SaveandCloseBtn_xp = "//td[contains(text(),'ave and Close')]";			
	// click on Submit		
	public String  PurchaseReqSubmitButton_xp="//div[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:2:AP1:SPsb2']//a[@class='xx3']";
	public String SelectRowspan_xp=  "//span[@class='x34b']";

	// Amount
	public String  PurchaseReq_ServicesAmount_xp = "//input[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:ItemI1:0:currencyAmountItemInfo::content']";



	public String AddAttachmentsIcon_xp = "//img[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:a1addrq:applicationsTable:_ATp:create::icon']";
	// drop down selection for attachment type
	public String AttchmentType_xp = "//select[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:a1addrq:applicationsTable:_ATp:attachmentTable:1:dCode::content']";
	//drop down selection for CategoryName
	public String AttchmentCategoryName_xp = "//select[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:a1addrq:applicationsTable:_ATp:attachmentTable:1:socCatName::content']";	
	//Requester to change to someone's on behalf 
	public String RequesterInput_xp= "//input[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:2:AP1:DelivB:0:requesterNameId::content']";

	//Purchase Order creation: 
	//Navigation to select PurachseOrder Icon from Home page: 
	public String  POIcon_xp = "//div[@id='itemNode_procurement_PurchaseOrders']//*[@class='svg-nav-cluster suiicon svg-bkgd07']";
	// Tasks bar under PO
	public String   POTasks_xp = "//img[@id='pt1:_FOr1:1:_FOSritemNode_procurement_PurchaseOrders:0:_FOTsdi__PrcPoPurchaseOrdersWorkarea_itemNode__FndTasksList::icon']";
	//Select "Process Requisitions"  from Tasks 
	public String ProcessRequisitions_xp =  "//a[text()='Process Requisitions']";  
	//Enter requisition number to search 
	public String RequisitionInput_xp = "//input[@class='x25']" ; 
	// click on SearchRequisition  Button
	public String SearchRequsitionButton_xp = "//button[text()='Search']";   

	public String ScrolltoLastcolumn = "//span[text()='Columns Hidden']";
	public String StrLocator=    "//div[@class='x1nn']/table[1]/tbody[1]/tr/td[5]/span/table[1]/tbody[1]/tr[1]/td[1]/a" ;
	public String Arwlocator1=   "//div[@class='x1nn']/table[1]/tbody[1]/tr[" ;
	public String Arwlocator2=  "]/td[1]";

	//table xpath for searchedOut results	

	public String AddToDocBuilder_xp ="//button[text()='Add to Document Builder']";    
	public String ClickOKbutton_xp =  "//button[text()='O']";  	
	public String ClickCreateButton_xp = "//button[text()='Create']";  
	public String ClickEditButton_xp = "//button[text()='Edit']";   
	public String ClickClearButton_xp ="//button[text()='Clear']";   
	public String ClickOKonPopupCOnfirm_xp = "//button[@id='d1::msgDlg::cancel']";
	public String ClickSubmitButton_xp =  "//div[contains(@id,'SPsb2')]"; 
	public String ClickOKAfterSubmitbutton_xp =  "//button[text()='OK']";		




	//  Manage Requisitions

	//ManageRequisitions link on PO home screen 
	public String ManageRequisitions_xp = "//a[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:0:SP4:r1:0:pt1:cl1']";
	//Entered By user
	public String EnteredByInput_xp = "//input[contains(@id,'q1:value10::content')]";			
	//Requisition  number
	public String RequisitionNumInput_xp = "//input[contains(@id,'q1:value20::content')]";
	//Status LOV
	public String StatusLOV_xp = "//select[contains(@id,'q1:value50::content')]";												
	//Search Button
	public String SearchButton_xp =  "//button[contains(@id,'q1::search')]";
	// select from search results first Row/first column of table
	public String RequisitionSearchedOut_xp = "//a[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:MAnt2:1:pt1:r1:0:ap1:r1:0:allMyReqsVCResult:_ATp:t1:0:cl5']";		


	public String EndofManageReqTableResults = "//div[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:MAnt2:1:pt1:r1:0:ap1:r1:0:allMyReqsVCResult:_ATp:j_id54']";		
	public String ManageReqTableResults = "//div[@class='x1nn']/table[1]/tbody[1]/tr/td[2]/div/table[1]/tbody[1]/tr[1]/span/td[3]/table[1]/tbody[1]/tr[1]/td[1]/a";
	public String ReqNumLink1 =  "//div[@class='x1nn']/table[1]/tbody[1]/tr[";			
	public String ReqNumLink2 =  "]/td[2]/div/table[1]/tbody[1]/tr[1]/td[3]/span/table[1]/tbody[1]/tr[1]/td[1]/a";

	public String ManageReqTableResults1 ="//div[@class='x1nn']/table/tbody/tr/td[@class='x12z']";		
	public String ReqNumLine1_xp = "//div[@class='x1nn']/table/tbody/tr/td";   //[@class='p_AFFocused x12z']"
	public String ReqNumLink_xp = "//div[@class='x1nn']/table[1]/tbody[1]/tr[1]/td[2]/div/table[1]/tbody[1]/tr[1]/td[3]/span/table[1]/tbody[1]/tr[1]/td[1]/a";		
	public String ClickActions_xp = "//a[@title='Actions']";	
	public String CancelRequisition_xp = "//td[contains(text(),'Cancel Requisition')]";

	public String EnterCancelReason_xp = "//textarea[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:MAnt2:1:pt1:r1:1:AP1:it1::content']";
	public String OkbuttonCancelReason_xp = "//button[@id='pt1:_FOr1:1:_FOSritemNode_my_information_purchase_requisitions:0:MAnt2:1:pt1:r1:1:AP1:cancelReasonDialog::ok']";
	public String  VerifyStatus_xp ="//a[contains(@id,'AP1:cl4')]";  // "//td[@class='xz0 xk8']";
	public String ClickDone_xp = "//span[text()='ne']";

	public String ConfirmButtonOK_xp =  "//button[text()='OK']"; 
	public String ConfirmButtonOK_xp1 = "//button[@type ='button']";
	public String ConfirmButtonOK_xp2 = "//button[@id='d1::msgDlg::cancel']"; 


	//Requisition With New Supplier:				
	// Supplier Number label
	public String NewSupplierlabel_xp = "//label[text()='New supplier']";			
	//SuplierName
	public String  NewSupplierinputbox_xp = "//input[contains(@id,'FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:ItemI1:0:it2::content')]";				  
	//SupplierSite
	public String SupplierSite_xp = "//input[contains(@id,'FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:ItemI1:0:inputText14::content')]";

	//Supplier Contact
	public String SupplierContact_xp="//input[contains(@id,'FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:ItemI1:0:inputText15::content')]";
	//Phone
	public String Phone_xp = "//input[contains(@id,'FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:ItemI1:0:inputText16::content')]";
	//Fax
	public String Fax_xp = "//input[contains(@id,'FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:ItemI1:0:inputText17::content')]";
	//Email
	public String Email_xp= "//input[contains(@id,'FOSritemNode_my_information_purchase_requisitions:0:_FOTsr1:1:pt1:AP1:s3:ItemI1:0:inputText18::content')]";	
	//Reassign requisitions   from Actions		
	public String ReqLinesArrayList = "//table[@summary='Requisition Lines']/tbody/tr/td[contains(@class,'x12z')]";
	public String ReqLines1stlocator ="//table[@summary='Requisition Lines']/tbody/tr[";
	public String ReqLines2ndLocator ="]/td[contains(@class,'x12z')]";

	public String ReaasignInActions_xp = "//td[contains(text(),'Reassign')]";
	public String InputReAssignTo_xp =  "//input[contains(@id,'listNameId::content')]";
	public String ClickOKReassign_xp =  "//button[contains(@id,'reassignRequisitionDialog::ok')]";
	public String ConfirmationDialog_xp=  "//div[@id='d1::msgDlg::_ccntr']";
	//public String ClickOKDialog_xp =  "//button[@id='d1::msgDlg::cancel']";
	//Reagents fields
	public String Reagents_xp = "//div[@class='x19']//table/tbody/tr/td/span[contains(@id,'RequisitionLineDFFIteratorreagent')]";	 	
	//My Receipts
	public String ReceiveItems_RequestorInput_xp = "//input[contains(@id,'QryId:value00::content')]";			                                          
	public String ReceiveItems_RequestingBU_xp  = "//select[contains(@id,'QryId:value40::content')]" ;//  "//select[@id='pt1:_FOr1:1:_FOSritemNode_my_information_self_service_receipts:0:_FOTsr1:0:ap1:QryId:value40::content']";
	public String ReceiveItems_POInput_xp  = "//input[contains(@id,'QryId:value50::content')]";   //"//input[@id='pt1:_FOr1:1:_FOSritemNode_my_information_self_service_receipts:0:_FOTsr1:0:ap1:QryId:value50::content']";
	public String ReceiveItems_Requisition_xp  = "//input[contains(@id,'QryId:value20::content')]";
	public String ReceiveItems_SearchButton_xp ="//button[contains(@id,'QryId::search')]";

	public String ReceiveItems_ReqRecordLink_xp = "//div[@class='x1nn']/table/tbody/tr[1]/td/div/table/tbody/tr/td/span/a[contains(@id,'commandLink3')]";
	public String ReceiveItems_PORecordLink_xp = "//div[@class='x1nn']/table/tbody/tr[1]/td/div/table/tbody/tr/td//a[contains(@id,'cl1')]";

	public String ClickReceive_xp = "//button[contains(@id,'ReceiveItemsReceiveButtonId')]";
	public String EnterQuantity_xp = "//input[contains(@id,'ap1:summaryAppsTable:_ATp:smryTb:0:it1::content')]";
	public String TxnDate_xp = "//input[contains(@id,'summaryAppsTable:_ATp:smryTb:0:inputDate1::content')]";			  
	public String CreateReceipts_SubmitButton_xp = "//div[contains(@id,'SPsb2')]"; 		  
	public String CreateReceipts_CancelButton_xp = "//div[contains(@id,'ap1:SPc')]"; 
	public String Confirm_dialog_xp = "//div[contains(@id,'ReceiptConfirmationDialogId')]/table/tbody/tr/td/span";								
	public String ClickConfirmOK_xp = "//button[contains(@id,'CfBtId')]";		
	public String ReceiveWarningMessge1_xp = "//div[@id='d1::msgDlg::_ccntr']";
	public String ClickOK_ReceiveWarningMessge1_xp = "//button[@id='d1::msgDlg::cancel']";		
	// Return the goods
	public String MyReceiptsTasks_xp = "//img[contains(@id,'_FOTsdiRcvSelfServWorkarea_FndTaskList::icon')]";				
	public String ManageReceipts_xp = "//a[contains(@id,'_FOTRaT:0:RAtl1')]";	

	public String ReceiptsNumInput_xp = "//input[contains(@id,'q1:value00::content')]";
	public String PONumInput_xp = "//input[contains(@id,'q1:value40::content')]"; 

	public String SearchButtonReceipts_xp = "//button[contains(@id,'q1::search')]";
	public String resultsArray_xp = "//div[@class='x1nn']/table/tbody/tr[1]/td[contains(@class,'x12z')]";

	public String ReturnButton_xp = "//button[contains(@id,'ap1:apTbl:_ATp:retBtn')]";
	public String CorrectButton_xp = "//button[contains(@id,'apTbl:_ATp:corBtn')]";
	public String ReturnQty_xp = "//input[contains(@id,'returnAppTable:_ATp:retTab:0:inputText1::content')]";
	public String ReturnReason_xp = "//input[contains(@id,'returnAppTable:_ATp:retTab:0:reasonNameId::content')]";

	public String SubmitButton_xp = "//div[contains(@id,'SPsb2')]";
	public String ConfimationDialog_xp = "//div//span[contains(@id,'ap1:activeOutputText2')]";
	public String OKButton_xp = "//button[contains(@id,'commandButton1')]";  

	// Create an Adhoc PO	
	// Navigate to Purchase orders-> and tasks> click on Create Order link
	public String CreateOrder_xp ="//a[contains(@id,'_FOTRaT:0:RAtl5')]";
	// select Style,
	public String  clickStyleLOV_xp= "//input[contains(@id,'StyleName::content')]"; 	
	// enter ProcurementBU
	public String   CreatePOProcurementBU_xp = "//select[contains(@id,'ProcurementBu::content')]";
	// enter RequisitionBU
	public String   CreatePORequisitionBU_xp = "//select[contains(@id,'RequisitioningBu::content')]";	
	public String   CreatePO_Currency_xp =  "//input[contains(@id,'Currency::content')]";				
	public String   CreatePO_BuyerName_xp =  "//input[contains(@id,'BuyerName::content')]";							
	// click on Create buttoon 
	public String  CreatePO_createButton_xp = "//button[contains(@id,'commandButton1')]";		
	// enter supplierInput
	public String   CreatePO_supplierInput_xp = "//input[contains(@id,'Supplier::content')]";				
	// enter suppliersiteInput
	public String  CreatePO_supplierSiteInput_xp = "//input[contains(@id,'SupplierSite::content')]";			

	// Go to Lines tab
	public String   CreatePO_LinesTab_xp = "//a[contains(@id,'lineDetailItemId::disAcr')]";			
	// click on + icon to add lines
	public String   ClcikAddIcon_xp = "//img[contains(@id,'_ATp:create::icon')]";		
	// Enter line Type 
	public String  LineType_xp = "//input[contains(@id,'LineType::content')]";
	public String  LineDescription_xp = "//input[contains(@id,'ItemDescription::content')]";
	public String  LineCategory_xp =  "//input[contains(@id,'Category::content')]";
	public String  LineSupplierItem_xp = "//input[contains(@id,'inputText9::content')]";
	public String  LineUOM_xp ="//input[contains(@id,'Uom::content')]";				
	public String  LineQuantity_xp = "//input[contains(@id,'s5:Quantity::content')]";				
	public String  BasePrice_xp = "//input[contains(@id,'ListPriceGoods::content')]";	
	public String  Location_xp =  "//input[contains(@id,'ShipToLocation::content')]";
	public String  ShipToOrg_xp = "//input[contains(@id,'ShipToOrg::content')]";			
	public String  DeliverToLoc_xp = "//input[contains(@id,'deliverToLocationId::content')]";
	public String  MatchApprovalsLevel_xp =  "//input[contains(@id,'MatchApprovalLevel::content')]";			
	public String  InvoiceMatchOption_xp = "//input[contains(@id,'selectOneChoice4::content')]";
	public String  POChanrgeAccount_xp = "//input[contains(@id,'LinePoChargeKffCS::content')]";	 		
	public String  ClickSaveButton_xp =  "//span[@class='xx6'][contains(text(),'Save')]";	
	public String EditPODocHeader_xp =  "//div[contains(@title,'Edit Document (Purchase Order):')]";

	// Manage Purachase Order-- communicate PO		
	public String   ManageOrder_xp = "//a[contains(@id,'_FOTRaT:0:RAtl4')]";		
	public String   KeywordsInput_xp = "//input[contains(@id,'q1:value00::content')]";
	public String   ProcuremntBUInput_xp = "//select[contains(@id,'q1:value10::content')]";
	public String   SupplierInput_xp = "//input[contains(@id,'q1:value20::content')]";
	public String   BuyerInput_xp = "//input[contains(@id,'q1:value30::content')]";
	public String   PurchaseOrderInput_xp = "//input[contains(@id,'q1:value40::content')]";
	public String   ReqInput_xp = "//input[contains(@id,'q1:value50::content')]";
	public String  	Status_xp = "//select[contains(@id,'q1:value60::content')]";
	public String 	ResultsFirstRow_xp = "//div[@class='x1nn']/table[1]/tbody[1]/tr[1]/td[1]";
	public String 	PONumLink_xp = "//div[@class='x1nn']/table[1]/tbody[1]/tr[1]/td[2]/div/table[1]/tbody[1]/tr[1]/td[3]/span/table[1]/tbody[1]/tr[1]/td[1]/a";				
	public String POCommunicate_xp = "//td[contains(text(),'Communicate')]";
	//Select Email Method
	public String SelectCommuMethodEMail_xp =  "//select[contains(@id,'CA_SupplierNotifMethodId::content')]";			
	// enter EMailID
	public String EnterEmail_xp = "//input[contains(@id,'it6::content')]";			
	// enter Fax
	public String EnterFax_xp = "//input[contains(@id,'it8::content')]";							
	//Click Ok
	public String ClickOK_xp = "//button[contains(@id,'d13::ok')]";		
	// capture ConfimMessage
	public String ConfirmationMessage_xp = "//div[@id='d1::msgDlg::_ccntr']";


	public String POLifeCycle_xp = "//td[@class='x1ts']";
	public String ViewDetailsBtn_xp = "//button[contains(text(),'View Details')]";  // or   "//button[contains(@id,'pt1:Order1:0:cb2')]" ;
	public String OrderedAmountText_xp = "//tr[contains(@id,':plam7')]";	
	public String POLinesTab_xp = "//a[contians(text(),'Lines')]";					
	public String POLines_All_xp ="//div[2]/table[1]/tbody[1]/tr/td[contains(@class,'x12z')]";		
	public String POLinesNum_All_xp = "//div[@class='x1nn']/table[1]/tbody[1]/tr/td/div/table/tbody/tr/td[4]";  										
	public String POLinesNumlocator1 =  "//div[@class='x1nn']/table[1]/tbody[1]/tr[";
	public String POLinesNumlocator2 =   "]/td/div/table/tbody/tr/td[4]";		
	public String POLinesNum1_xp ="//div[@class='x1nn']/table[1]/tbody[1]/tr[1]/td[2]/div/table[1]/tbody[1]/tr[1]/td[4]/span";	
	public String ClickLinesActions_xp = "//div[contains(@id,':_ATp:ATm')]";      
	public String ClickCancelLine_xp = "//td[contains(text(),'Cancel Line')]";
	public String WarningMessage1_xp = "//td[contains(@id,'AP2:d11::contentContainer')]";
	public String YesWarningMessage1_xp= "//button[contains(@id,'AP2:d11::yes')]";
	public String NoWarningMessage1_xp= "//button[contains(@id,'AP2:d11::no')]";

	//if there is only one line  we get another warning message:			
	public String WarningMessage2_xp = "//td[contains(@id,'AP2:d11::contentContainer')]";
	public String YesWarningMessage2_xp= "//button[contains(@id,'AP2:d11::yes')]";				
	public String  InputReasonforCancel_xp = "//textarea[contains(@id,'AP2:it4::content')]";
	public String OkOnCancelDokPopup_xp= "//button[contains(@id,'AP2:d13::ok')]";   
	public String CancelDocument_xp = "//td[contains(text(),'Cancel Document')]";



	// Change Order 
	public String EditOrder_xp = "//tr[contains(@id,':AP1:EditOrderMenuItem')]/td[contains(text(),'Edit Order')]";
	public String EditOrderbyBuyer_xp ="//tr[contains(@id,':AP2:CA_HEADER_EDIT')]/td[contains(text(),'Edit')]";		
	public String ViewDocumentHistory_xp = "//td[contains(text(),'View Document History')]";			
	public String WarningChangeOrder_xp = "//td[contains(@id,'::contentContainer')]";
	public String YesbuttonWaring1_xp = "//button[contains(@id,':d1::yes')]";
	public String EnterEditOrderDesc_xp = "//textarea[contains(@id,':inputText18::content')]";


	public String View_xp ="//div[contains(@title,'View')]";
	public String CollapseAll_xp ="//tr[contains(@id,':_ATTp:_cpsAll')]";
	public String ArrayList_xp = "//div[contains(@id,'_ATTp:tt1::db')]/table/tbody/tr/td[contains(@class,'x12z')]";

	public String ArrayList2_xp =   "//div[contains(@id,'_ATTp:ttab1::db')]/table/tbody/tr/td[contains(@class,'x12z')]";
	public String ActionPerformed_xp = "//div[contains(@id,'_ATTp:ttab1::db')]/table/tbody/tr/td/div/table/tbody/tr/td/div/span[@class='x2s3']" ; 

	public String ActionPerformedBy1_xp = "//div[contains(@id,'_ATTp:tt1::db')]/table/tbody/tr/td/div/table/tbody/tr/td//span/a[contains(@id,'tt1:";
	public String ActionPerformedBy2_xp =	 ":cl2')]";

	public String InvManagementIcon_xp =  "//div[@title='Inventory Management']";
	public String InvQuickSearch_xp = "//div[@title='Quick Search']";
	public String InvSelectRecLines_xp =  "//select[contains(@id,'cList::content')]";  //'Received Lines'
	public String InvAdavanceLink_xp = "//a[contains(@id,'cl1')]";   // Advanced link

	public String InvReceiptInput_xp =  "//input[contains(@id,'ap1:q1:value10::content')]";

	public String InvPOInput_xp = "//input[contains(@id,'ap1:q1:value20::content')]";

	public String InvPOLovIcon_xp = "//a[contains(@id,'ap1:q1:value20::lovIconId')]";
	public String InvPODropDSearch_xp = "//a[contains(@id,'ap1:q1:value20::dropdownPopup::popupsearch')]" ;	
	public String InvPOLOvInput_xp = "//input[contains(@id,'ap1:q1:value20::_afrLovInternalQueryId:value00::content')]";
	public String InvPOLOvSearchButton_xp = "//button[contains(@id,'ap1:q1:value20::_afrLovInternalQueryId::search')]";
	public String InvPOLOvRecord_xp = "//div[contains(@id,'ap1:q1:value20_afrLovInternalTableId::db')]/table/tbody/tr/td[contains(@class,'x12z')]";
	public String InvPOLOvOKbutton_xp = "//button[contains(@id,'ap1:q1:value20::lovDialogId::ok')]";


	public String InvSearchButton_xp = "//button[contains(@id,'ap1:q1::search')]"; 

	public String InvLinesResults_xp = "//div[contains(@id,':_ATp:table1::db')]/table/tbody/tr/td[contains(@class,'x12z')]";

	public String InvLinesResLineStatus_xp = "//div[contains(@id,':_ATp:table1::db')]/table/tbody/tr/td/div/table/tbody/tr/td[4]";
	public String InvLinesResLineQty_xp = "//div[contains(@id,':_ATp:table1::db')]/table/tbody/tr/td/div/table/tbody/tr/td[8]";

	/*****************************************************/

	/************************end of page********************************/






}
