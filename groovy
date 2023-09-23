import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import com.boomi.execution.ExecutionUtil;
import java.util.Date

logger = ExecutionUtil.getBaseLogger();

def CHANGE_DATE_START=ExecutionUtil.getDynamicProcessProperty("CHANGE_DATE_START")
def CHANGE_TIME_START=ExecutionUtil.getDynamicProcessProperty("CHANGE_TIME_START")
def CHANGE_DATE_END=ExecutionUtil.getDynamicProcessProperty("CHANGE_DATE_END")
def CHANGE_TIME_END=ExecutionUtil.getDynamicProcessProperty("CHANGE_TIME_END")

logger.info("-----Input date and time-------")
logger.info("CHANGE_DATE_START"+CHANGE_DATE_START);
logger.info("CHANGE_TIME_START"+CHANGE_TIME_START);
logger.info("CHANGE_DATE_END"+CHANGE_DATE_END);
logger.info("CHANGE_TIME_END"+CHANGE_TIME_END);

Date date = new Date()

// Code For Processing Change Start Date

if(CHANGE_DATE_START !=null && !(CHANGE_DATE_START.equals(""))) {
	if(CHANGE_TIME_START !=null && !(CHANGE_TIME_START.equals(""))){

		if( Date.parse("yyyyMMdd",CHANGE_DATE_START) && Date.parse("HHmmss",CHANGE_TIME_START)  )	 
		{
			
			date = Date.parse( 'yyyyMMdd', CHANGE_DATE_START )
			CHANGE_DATE_START = date.format('yyyy-MM-dd');
			
			date = Date.parse( 'HHmmss', CHANGE_TIME_START )
			CHANGE_TIME_START=date.format('HH:mm:ss')
		}
		
		else {
			
			IsFilterError = "1"	;
			FilterError = "Error in Change Start Date Time Context Value. Kinldy Make Sure the change start date and time should have proper value with the  following format Change Start Date ='yyyyMMdd' and Change Start Time = 'HHmmss' \n ";
		}

	}

	else {
		if( Date.parse("yyyyMMdd",CHANGE_DATE_START) )
		{
			
			date = Date.parse( 'yyyyMMdd', CHANGE_DATE_START )
			CHANGE_DATE_START = date.format('yyyy-MM-dd');
			CHANGE_TIME_START=date.format('HH:mm:ss');
			
		}
		else {
			IsFilterError = "1";
			FilterError = "Error in Change Start Date Context Value. Kinldy Make Sure the change start date should have proper value with the following format 'yyyyMMdd' \n";
			
		}
		
	
		
	}
}



// Code For Processing Chnage End Date
if(CHANGE_DATE_END !=null && !(CHANGE_DATE_END.equals(""))) {

	if(CHANGE_TIME_END !=null && !(CHANGE_TIME_END.equals(""))){

		if( Date.parse("yyyyMMdd",CHANGE_DATE_END) && Date.parse("HHmmss",CHANGE_TIME_END)  )	 
		{
			date = Date.parse( 'yyyyMMdd', CHANGE_DATE_END )
			CHANGE_DATE_END = date.format('yyyy-MM-dd');
			
			date = Date.parse( 'HHmmss', CHANGE_TIME_END )
			CHANGE_TIME_END=date.format('HH:mm:ss')
		}
		
		else {
			
			IsFilterError = "1"	;
			FilterError += "Error in Change End Date Time Context Value. Kinldy Make Sure the change end date and time should have proper value with the  following format Change End Date ='yyyyMMdd' and Change End Time = 'HHmmss' \n ";
		}

	}

	else {
		if( Date.parse("yyyyMMdd",CHANGE_DATE_END) )
		{
			
			CHANGE_TIME_END='235959';
			date = Date.parse( 'yyyyMMdd', CHANGE_DATE_END );
			CHANGE_DATE_END = date.format('yyyy-MM-dd');
			date = Date.parse( 'HHmmss', CHANGE_TIME_END)
			CHANGE_TIME_END=date.format('HH:mm:ss');
			
		}
		else {
			IsFilterError = "1";
			FilterError += "Error in Change End Date Context Value. Kinldy Make Sure the change End date should have proper value with the following format 'yyyyMMdd' \n";
			
		}
		
	}
}

com.boomi.execution.ExecutionUtil.setDynamicProcessProperty("CHANGE_DATE_START",CHANGE_DATE_START,false);
com.boomi.execution.ExecutionUtil.setDynamicProcessProperty("CHANGE_TIME_START",CHANGE_TIME_START,false);
com.boomi.execution.ExecutionUtil.setDynamicProcessProperty("CHANGE_DATE_END",CHANGE_DATE_END,false);
com.boomi.execution.ExecutionUtil.setDynamicProcessProperty("CHANGE_TIME_END",CHANGE_TIME_END,false);


logger.info("-----Output date and time-------")
logger.info("CHANGE_DATE_START"+CHANGE_DATE_START);
logger.info("CHANGE_TIME_START"+CHANGE_TIME_START);
logger.info("CHANGE_DATE_END"+CHANGE_DATE_END);
logger.info("CHANGE_TIME_END"+CHANGE_TIME_END);

for (int i = 0; i < dataContext.getDataCount(); i++) {
InputStream is = dataContext.getStream(i);
Properties props = dataContext.getProperties(i);
dataContext.storeStream(is, props);
}
