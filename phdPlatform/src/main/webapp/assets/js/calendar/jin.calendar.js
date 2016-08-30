
/* global data1 */

var dayData = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
var MonthData = ['January', 'February', 'March', 'Aprill', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
var testObj, testObj2, testObj3;

var date1 = new Date();
var currentDate = new Date();
var ciclo;
var curriculum;
var jinCalendar = new Object();

jinCalendar.init = function(type, email) {
        tipoAccount = type;
        emailPrimaria = email;
        
            setCalendar(date1);
        
        
           
};


//*** PER I DOCENTI/ADMIN ****
jinCalendar.getScheduleDate1= function() {
        
	$.getJSON("GetAllCourse", function (data) {
            $.each(data.course, function (index, value) {
                var dataInizio= value.startDate;
                dataInizio = dataInizio.toString();
                
                var startDate = (dataInizio.substring(0,4))+(dataInizio.substring(5,7));
                var startDay =(dataInizio.substring(8,10));
                var startDay = parseInt(startDay);
                
                var dataFine= value.endDate;
                dataFine = dataFine.toString();
                var endDate = (dataFine.substring(0,4)+dataFine.substring(5,7));
                
                var endDay =(dataFine.substring(8,10));
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
                jinCalendar.setScheduleCourse();
                $.getJSON("GetAllLessonServlet",{fkCourse: value.idCourse}, function (data1) {
                if(data1.result){
                   $.each(data1.lessons, function (index, value1) {
                        
                        dataInizio= value1.data;
                        dataInizio = dataInizio.toString();
                        var startDate = (dataInizio.substring(0,4))+(dataInizio.substring(5,7));
                        var startDay =(dataInizio.substring(8,10));
                        var startDay = parseInt(startDay);
                        var inizio = value1.startTime;
                        inizio=inizio.toString();
                        var fine = value1.endTime;
                        fine = fine.toString();
                        //oggetto lezione
                        testObj2 = [{
                            yyyymm : startDate,
                            day : startDay,
                            type : '1',
                            title: 'Lezione di '+value1.name + '<br> classe: '+value1.classroom ,
                            text : value1.description + '<br> Inizio Lezione: '+ inizio + '<br> Fine lezione: '+fine,
                            idLesson : value1.idLesson
                           }];
                        
                        jinCalendar.setScheduleLesson();
                        
                    });
                    
                }
                
                });
        
            });
            
        });
        $.getJSON("GetAllSeminarServlet", function (data2) {
            
            $.each(data2.seminar, function (index, value) {
                 dataInizio= value.data;
                dataInizio = dataInizio.toString();
                
                var startDate = (dataInizio.substring(0,4))+(dataInizio.substring(5,7));
                var startDay =(dataInizio.substring(8,10));
                var startDay = parseInt(startDay);
                var inizio = value.startTime;
                inizio=inizio.toString();
                var fine = value.endTime;
                fine = fine.toString();
                
                //oggetto seminario
                testObj3 = [{
                    yyyymm : startDate,
                    day : startDay,
                    type : '2',
                    title: 'Seminario di '+value.name,
                    text : value.description + '<br> luogo: '+value.place,
                    idSeminar : value.idSeminar
                }];
            jinCalendar.setScheduleSeminar();
                
        });
    });	
};


//*** PER I DOTTORANDI ***
jinCalendar.getScheduleDate2= function() {
        $.getJSON("GetAccountbyEmail", {index: emailPrimaria}, function(data){
            ciclo = data.fkCycle;
            curriculum = data.fkCurriculum;
          
        });
        $.getJSON("getCourseListId",{cycle: ciclo, curriculum: curriculum}, function(data){
            
            $.each(data.courses, function (index, value1) {
                $.getJSON("GetCourseServlet",{idCourse: value1}, function (data) {
                        var dataInizio= data.startDate;
                        dataInizio = dataInizio.toString();
                        var startDate = (dataInizio.substring(0,4))+(dataInizio.substring(5,7));
                        var startDay =(dataInizio.substring(8,10));
                        var startDay = parseInt(startDay);
                        var dataFine= data.endDate;
                        dataFine = dataFine.toString();
                        var endDate = (dataFine.substring(0,4)+dataFine.substring(5,7));
                        var endDay =(dataFine.substring(8,10));
                        var endDay = parseInt(endDay);
                        //oggetto corso
                        testObj = [{
                            yyyymm : startDate,
                            day : startDay,
                            type : '0',
                            title: 'Inizio del corso di '+data.name,
                            text : data.description
                        },
                        {
                            yyyymm : endDate,
                            day : endDay,
                            type : '0',
                            title: 'Fine del corso di '+data.name,
                            text : data.description 
                        }];
                        jinCalendar.setScheduleCourse();
                        $.getJSON("GetAllLessonServlet",{fkCourse: value1}, function (data1) {
                        if(data1.result){                            
                            $.each(data1.lessons, function (index, value) {
                  
                            dataInizio= value.data;
                            dataInizio = dataInizio.toString();
                            
                            var startDate = (dataInizio.substring(0,4))+(dataInizio.substring(5,7));
                            var startDay =(dataInizio.substring(8,10));
                            var startDay = parseInt(startDay);
                            var inizio = value.startTime;
                            inizio=inizio.toString();
                            var fine = value.endTime;
                            fine = fine.toString();
                            //oggetto lezione
                            testObj2 = [{
                                yyyymm : startDate,
                                day : startDay,
                                type : '1',
                                title: 'Lezione di '+value.name + '<br> classe: '+value.classroom ,
                                text : value.description + '<br> Inizio Lezione: '+ inizio + '<br> Fine lezione: '+fine,
                                idLesson : value.idLesson
                             }];
                             jinCalendar.setScheduleLesson();
                        
                     });
                    }
                 });
                 $.getJSON("getAllSeminarOf",{fkCourse: value1}, function (data2) {

                    if(data2.result){  
                    $.each(data2.seminar, function (index, value) {
                        dataInizio= value.data;
                        dataInizio = dataInizio.toString();
                        var startDate = (dataInizio.substring(0,4))+(dataInizio.substring(5,7));
                        var startDay =(dataInizio.substring(8,10));
                        var startDay = parseInt(startDay);
                        var inizio = value.startTime;
                        inizio=inizio.toString();
                        var fine = value.endTime;
                        fine = fine.toString();
                        //oggetto seminario
                        testObj3 = [{
                            yyyymm : startDate,
                            day : startDay,
                            type : '2',
                            title: 'Seminario di '+value.name,
                            text : value.desription + '<br> luogo: '+value.place,
                            idSeminar : value.idSeminar

                         }];
                        jinCalendar.setScheduleSeminar();
                
                    });
                }
                });	
             });
         });
                
     });
    
	
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
        if (tipoAccount === 'professor'){
            jinCalendar.getScheduleDate1();
        }
        if (tipoAccount === 'phdstudent'){
            jinCalendar.getScheduleDate2();
        }
}

function FormatMe(n) {
	return (n < 10) ? '0' + n : n;
}

jinCalendar.setScheduleCourse = function() {
        //oggetto corso
	var obj = testObj ;
	var sType = [ 'btn-default', 'btn-primary',
			'btn-success', 'btn-info', 'btn-warning', 'btn-danger' ];

	for ( var n in obj) {
		var item = obj[n];

		var html = '<button type="button" class="btn {TYPE} standard-description-course btn-xs" data-toggle="tooltip" data-placement="top" title="{TEXT}" descr="{TITLE}">{TITLE}</button>';

		html = html.replace('{TITLE}',
				item.title).replace('{TYPE}',
				sType[item.type])
				.replace('{TEXT}', item.text).replace('{TITLE}',
				item.title);;
                var data = date1.getFullYear() + '' + FormatMe(date1.getMonth() + 1) + '';
                
                if(data===item.yyyymm){
                    var strDefault = $('#td-' + item.day).html();
                    $('#td-' + item.day).html(strDefault + html);
                }
            }
     ///CLICK SUL CORSO
   $('.standard-description-course').click(function() {
                $("#titleInfo").html("");
                $("#descriptionInfo").html("");
                $("#infoDialog").modal();
                title = $(this).attr('descr');
                text = $(this).attr('title');
                $("#titleInfo").html(title);
                $("#descriptionInfo").html(text);
	});

	$("td>div").css({
		'max-height' : '90px',
		'overflow-y' : 'auto'
	}).addClass('scrolltest');   
      
};
jinCalendar.setScheduleLesson = function() {            
        //oggetto lezione    
	var obj = testObj2 ;
	var sType = [ 'btn-default', 'btn-primary',
			'btn-success', 'btn-info', 'btn-warning', 'btn-danger' ];

	for ( var n in obj) {
		var item = obj[n];

		var html = '<button type="button" class="btn {TYPE} standard-description-lesson btn-xs" data-toggle="tooltip" data-placement="top" title="{TEXT}" descr="{TITLE}" idLesson="{IDLESS}">{TITLE}</button>';

		html = html.replace('{TITLE}',
				item.title).replace('{TYPE}',
				sType[item.type])
				.replace('{TEXT}', item.text).replace('{TITLE}',
				item.title).replace('{IDLESS}', item.idLesson);
                var data = date1.getFullYear() + '' + FormatMe(date1.getMonth() + 1) + '';
                
                if(data===item.yyyymm){
                    var strDefault = $('#td-' + item.day).html();
                    $('#td-' + item.day).html(strDefault + html);
                }

	}
     ///CLICK SULLA LEZIONE
   $('.standard-description-lesson').click(function() {
                $("#titleInfo1").html("");
                $("#descriptionInfo1").html("");
                $("#infoDialog1").modal();
                title = $(this).attr('descr');
                text = $(this).attr('title');
                idLesson = $(this).attr('idLesson');
                try{
	                document.getElementById('idLess').value = idLesson;
	                document.getElementById('idLess2').value = idLesson;
                } catch(e){}
                document.getElementById('idLess3').value = idLesson;
                $("#titleInfo1").html(title);
                $("#descriptionInfo1").html(text);
	});

	$("td>div").css({
		'max-height' : '90px',
		'overflow-y' : 'auto'
	}).addClass('scrolltest'); 
 };       
	


jinCalendar.setScheduleSeminar = function() {
        //oggetto seminario
	var obj = testObj3;
	var sType = [ 'btn-default', 'btn-primary',
			'btn-success', 'btn-info', 'btn-warning', 'btn-danger' ];

	for ( var n in obj) {
		var item = obj[n];

		var html = '<button type="button" class="btn {TYPE} standard-description-seminar btn-xs" data-toggle="tooltip" data-placement="top" title="{TEXT}"  descr="{TITLE}" idSeminar="{IDSEMI}">{TITLE}</button>';

		html = html.replace('{TITLE}',
				item.title).replace('{TYPE}',
				sType[item.type])
				.replace('{TEXT}', item.text).replace('{TITLE}',
				item.title).replace('{IDSEMI}', item.idSeminar);;
                var data = date1.getFullYear() + '' + FormatMe(date1.getMonth() + 1) + '';
                
                if(data===item.yyyymm){
                    var strDefault = $('#td-' + item.day).html();
                    $('#td-' + item.day).html(strDefault + html);
                }
            }
         
       ///CLICK SUL SEMINARIO
   $('.standard-description-seminar').click(function() {
                 $("#titleInfo2").html("");
                $("#descriptionInfo2").html("");
                $("#infoDialog2").modal();
                title = $(this).attr('descr');
                text = $(this).attr('title');
                idSeminar= $(this).attr('idSeminar');
                document.getElementById('idSemi').value = idSeminar;
                document.getElementById('idSemi2').value = idSeminar;
                $("#titleInfo2").html(title);
                $("#descriptionInfo2").html(text);
	});

	$("td>div").css({
		'max-height' : '90px',
		'overflow-y' : 'auto'
	}).addClass('scrolltest');    

};



