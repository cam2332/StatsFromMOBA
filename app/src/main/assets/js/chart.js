google.load("visualization", "1", {'packages':['corechart']});
google.setOnLoadCallback(drawChart);

const timeRange = {
LASTDAY: 0,
LASTWEEK: 1,
LASTMONTH: 2,
MONTH: 3,
YEAR: 4,
}

var current = 0;

function nextDataTypeIndex() {
    current = current == 4 ? 0 : current + 1
}


function drawChart() {


var data = [];
data[0] = google.visualization.arrayToDataTable(lastDay);
data[1] = google.visualization.arrayToDataTable(lastWeek);
data[2] = google.visualization.arrayToDataTable(lastMonth);
data[3] = google.visualization.arrayToDataTable(months);
data[4] = google.visualization.arrayToDataTable(years);

var options = {
    legend: { position: 'top', maxLines: 3 },
    bar: { groupWidth: '45%' },
    series: {
        0:{color: '#1e88e5'},
        1:{color: '#e4002b'},
    },
    isStacked: true,
    animation:{
        duration: 500,
        easing: 'out',
        startup: true,
    },
    chartArea: {
        width: '90%',
        height: '90%',
    }
};

var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
var button = document.getElementById('b1');

chart.draw(data[current], options);

button.onclick = function() {
    console.log(`Current value: ${current}`);
    nextDataTypeIndex();
    console.log(`Current value after change: ${current}`);

    chart.draw(data[current], options);
}
}