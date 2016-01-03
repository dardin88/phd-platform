
var dayData = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
var MonthData = ['January', 'February', 'March', 'Aprill', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
var testObj;

var date1 = new Date();
var currentDate = new Date();

var jinCalendar = new Object();

jinCalendar.init = function() {
	setCalendar(date1);
};



jinCalendar.getScheduleDate = function() {
	$.getJSON("GetAllCourse", function (data) {
            
            $.each(data.course, function (index, value) {
                var dataInizio= value.startDate;
                dataInizio = dataInizio.toString();
                var startDate = (dataInizio.substring(0,4))+(dataInizio.substring(8,10));
                var startDay =(dataInizio.substring(5,7));
                var startDay = parseInt(startDay);
                corso = value.idCourse;
                var dataFine= value.endDate;
                dataFine = dataFine.toString();
                var endDate = (dataFine.substring(0,4)+dataFine.substring(8,10));
                
                var endDay =(dataFine.substring(5,7));
                var endDay = parseInt(endDay);
                //oggetto corso
                testObj = [{
                    yyyymm : startDate,
                    day : startDay,
                    type : '0',
                    title: 'Inizio del corso di '+value.name,
                    text : value.description
                },
                {
                    yyyymm : endDate,
                    day : endDay,
                    type : '0',
                    title: 'Fine del corso di '+value.name,
                    text : value.description 
                }];
                
                $.getJSON("GetAllLessonServlet",{fkCourse: corso}, function (data1) {
           
                   $.each(data1.lessons, function (index, value1) {
                  
                 dataInizio= value1.data;
                                

                dataInizio = dataInizio.toString();
                var startDate = (dataInizio.substring(0,4))+(dataInizio.substring(8,10));
                var startDay =(dataInizio.substring(5,7));
                var startDay = parseInt(startDay);
                
               
               //oggetto lezione
                testObj2 = [{
                    yyyymm : startDate,
                    day : startDay,
                    type : '1',
                    title: 'Lezione di '+value1.name + ' classe: '+value1.classroom ,
                    text : value1.description + 'Inizio Lezione: '+value1.startTime + ' Fine lezione: '+value1.endTime
                }];
                
                
            });
            
        });
            });
            
        });
         
        
	

	jinCalendar.setSchedule();
};

function prev() {
    
	date1 = new Date(date1.getFullYear(), date1.getMonth()-1, date1.getDate());
	setCalendar(date1);
	if(jinCalendar.prev !== null) {
		jinCalendar.prev(date1);	
              
	}
	
}

function next() {
	date1 = new Date(date1.getFullYear(), date1.getMonth()+1, date1.getDate());
	setCalendar(date1);
	if(jinCalendar.next !== null) {
		jinCalendar.next(date1);
	}
}

function setCalendar(date1) {

	var initHTML = '<table class="table table-bordered"><tr>';
	for(var i=0; i<7; i++) {
		initHTML += '<td>' + dayData[i] + '</td>';
	}
	initHTML += '</tr></table>';

	$('.div-init').html(initHTML);

	var lastDate = (new Date(date1.getFullYear(), date1.getMonth()+1, 0)).getDate();
	var beforeLastDate = (new Date(date1.getFullYear(), date1.getMonth(), 0)).getDate();

	$('.h4-month').text('Year ' + date1.getFullYear() + '. ' + MonthData[date1.getMonth()] + '/' + (date1.getMonth()+1));

	var nDay = 0;
	var startDay = (new Date(date1.getFullYear(), date1.getMonth(), 1)).getDay();

	var weekData = '<tr>';

	// Before Days
	for(var i=0; i<startDay; i++) {
		weekData += '<td><font color=#c0c0c0><span>' + (beforeLastDate - (startDay-i)) +'</span></font></td>';
		if(i>6) {
			weekData += '</tr>';
			$('table>tbody').append(weekData);
			weekData = '<tr>';
			nDay = 0;
		}
		nDay++;
	}

	for(var i=1 ; i<=lastDate; i++) {

		var nDayNumber = '<font color={COLOR}>' + i + '</font>';

		if(nDay === 0) {
			nDayNumber = nDayNumber.replace('{COLOR}', '#ff3737');
		} else if(nDay === 6) {
			nDayNumber = nDayNumber.replace('{COLOR}', '#007cf7');
		} else {
			nDayNumber = nDayNumber.replace('{COLOR}', '#727272');
		}

		
		if(currentDate.getFullYear() === date1.getFullYear() && currentDate.getMonth() === date1.getMonth() && currentDate.getDate() === i) {
			weekData += '<td style="background-color: #ffe9ae""><div id="td-' + i + '"' + nDayNumber +'</div></td>';
		} else {
			weekData += '<td><div id="td-' + i + '"' + nDayNumber +'</div></td>';	
		}

		if(nDay >= 6) {
			weekData += '</tr>';
			$('table>tbody').append(weekData);
			weekData = '<tr>';
			nDay = 0;
		} else {
			nDay++;
		}
		
	}

	var n = 1;
	for(var i=nDay; i<7; i++) {
		weekData += '<td><font color=#c0c0c0>' + n++ +'</font></td>';
	}
	weekData += '</tr>';
	$('table>tbody').append(weekData);

	//jinCalendar.getScheduleDate(date.getFullYear() + FormatMe(date.getMonth() + 1) + '');
	jinCalendar.getScheduleDate();
}

function FormatMe(n) {
	return (n < 10) ? '0' + n : n;
}

jinCalendar.setSchedule = function() {
        //oggetto corso
	var obj = testObj ;
	var sType = [ 'btn-default', 'btn-primary',
			'btn-success', 'btn-info', 'btn-warning', 'btn-danger' ];

	for ( var n in obj) {
		var item = obj[n];

		var html = '<button type="button" class="btn {TYPE} standard-description btn-xs" data-toggle="tooltip" data-placement="top" title="{TEXT}">{TITLE}</button>';

		html = html.replace('{TITLE}',
				item.title).replace('{TYPE}',
				sType[item.type])
				.replace('{TEXT}', item.text);
                var data = date1.getFullYear() + '' + FormatMe(date1.getMonth() + 1) + '';
                
                if(data===item.yyyymm){
                    var strDefault = $('#td-' + item.day).html();
                    $('#td-' + item.day).html(strDefault + html);
                }
            }
            
        //oggetto lezione    
	var obj = testObj2 ;
	var sType = [ 'btn-default', 'btn-primary',
			'btn-success', 'btn-info', 'btn-warning', 'btn-danger' ];

	for ( var n in obj) {
		var item = obj[n];

		var html = '<button type="button" class="btn {TYPE} standard-description btn-xs" data-toggle="tooltip" data-placement="top" title="{TEXT}">{TITLE}</button>';

		html = html.replace('{TITLE}',
				item.title).replace('{TYPE}',
				sType[item.type])
				.replace('{TEXT}', item.text);
                var data = date1.getFullYear() + '' + FormatMe(date1.getMonth() + 1) + '';
                
                if(data===item.yyyymm){
                    var strDefault = $('#td-' + item.day).html();
                    $('#td-' + item.day).html(strDefault + html);
                }

	}

	$('.standard-description').click(function() {
		alert($(this).attr('title'));
	});

	$("td>div").css({
		'max-height' : '90px',
		'overflow-y' : 'auto'
	}).addClass('scrolltest');

};
