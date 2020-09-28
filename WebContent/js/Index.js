var now;
function setfig(num) {
    var ret;
    if( num < 10 ) { ret = "0" + num; }
    else { ret = num; }
    return ret;
}
function showClock() {
    var nowTime = new Date();
    var nowHour = setfig( nowTime.getHours() );
    var nowMin  = setfig( nowTime.getMinutes() );
    var msg ="現在時刻</br>"+nowHour + ":" + nowMin;
    document.getElementById("time").innerHTML = msg;
    now=nowHour+""+nowMin;
    now=Number(now);
}
showClock();
setInterval('showClock()',5000);

var startList=[];
var endList=[];
var questList=[];
var contentList=[];
for(let i = 0;i< count ; i++) {
    startList.push(Number(document.getElementById("startTime"+i).innerHTML.replace(":", '')));
    endList.push(Number(document.getElementById("endTime"+i).innerHTML.replace(":", '')));
    questList.push(document.getElementById("quest"+i).innerHTML);
    contentList.push(document.getElementById("content"+i).innerHTML);
}

function nowItem(){
    for(let i = 0;i< count ; i++) {
        if(now>=startList[i]&&now<=endList[i]){
            return i;
        }
    }

    return -1;
}
function lag(data0,data1){
    var ret= data0-data1;
    if(ret<0){
        ret=ret*-1;
    }
    return ret;
}
function Hour(lag){
    var data0= lag/100;
    var data1=parseInt(data0);


    return data1;
}

function Min(lag){
    var data0= lag/100;
    var data1=parseInt(data0)*100;
    var data2=lag-data1;

    if(data2>59){
        data2=(data2-40);
    }
    return data2;
}

function totalMinF(Hour,Min){
    var ret=Hour*60+Min;
    return ret;
}

function totalF(Hour,Min){
    var colon=":";
    if(Min<10){
        colon=":0";
    }
    var ret=Hour+colon+Min;
    return ret;
}

function datas(){
    var item= nowItem();
    if(item>-1){
        var quest= questList[item];
        document.getElementById("nowQuest").innerHTML = quest;
        var content= contentList[item];
        document.getElementById("content").innerHTML = "詳細</br>　</br>"+content;
        var startTime=document.getElementById("startTime"+item).innerHTML;
        document.getElementById("startTime").innerHTML = "開始時間</br>"+startTime;
        var endTime=document.getElementById("endTime"+item).innerHTML;
        document.getElementById("endTime").innerHTML = "終了時間</br>"+endTime;
        var limtLag= lag(endList[item],now);
        var nowLag= lag(startList[item],now);
        var totalLag= lag(startList[item],endList[item]);
        console.log(Min(nowLag));

        var min=Min(totalLag);
        var colon=":";
        if(min<10){
            colon=":0";
        }

        var totalTime= totalF(Hour(totalLag),Min(totalLag));
        var totalMin= totalMinF(Hour(totalLag),Min(totalLag));
        document.getElementById("totalTime").innerHTML = "総時間</br>"+totalTime+"("+totalMin+"分)";


        var Timer=totalF(Hour(nowLag),Min(nowLag));
        var TimerMin=totalMinF(Hour(nowLag),Min(nowLag));
        document.getElementById("Timer").innerHTML = "経過</br>"+Timer+"("+TimerMin+"分)";


        var limt=totalF(Hour(limtLag),Min(limtLag));
        var limtMin=totalMinF(Hour(limtLag),Min(limtLag));
        document.getElementById("limtTime").innerHTML = "残り</br>"+limt+"("+limtMin+"分)";


        document.getElementById("progress").value = TimerMin/totalMin*100;


    }
}
datas();
setInterval('datas()',10000);
