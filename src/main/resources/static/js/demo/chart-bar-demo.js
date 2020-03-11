// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Bar Chart Example
var ctx = document.getElementById("myBarChart");
var dataChart = [];
var labelChart = [];
var dataChartSort = [];
for(var i=0;i<vm.chartDataVMS2.length;i++) {
  dataChart.push(vm.chartDataVMS2[i].value);
  dataChartSort.push(vm.chartDataVMS2[i].value);
  labelChart.push(vm.chartDataVMS2[i].label);
}
dataChartSort.sort((a,b)=>a-b);
var j  = dataChartSort.length -1;
var max = dataChartSort[j];
console.log(max);
var myLineChart = new Chart(ctx, {
  type: 'bar',
  data: {
    labels: labelChart,
    datasets: [{
      label: "Revenue",
      backgroundColor: "rgba(2,117,216,1)",
      borderColor: "rgba(2,117,216,1)",
      data: dataChart,
    }],
  },
  options: {
    scales: {
      xAxes: [{
        time: {
          unit: 'month'
        },
        gridLines: {
          display: false
        },
        ticks: {
          maxTicksLimit: 32
        }
      }],
      yAxes: [{
        ticks: {
          min: 0,
          max: max + 2,
          maxTicksLimit: 5
        },
        gridLines: {
          display: true
        }
      }],
    },
    legend: {
      display: false
    }
  }
});
