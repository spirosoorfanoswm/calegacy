import {Component, OnInit, Input, OnChanges} from '@angular/core';

@Component({
    selector: 'app-statistics-graph',
    templateUrl: './statistics-graph.component.html',
    styleUrls: ['./statistics-graph.component.css']
})
export class StatisticsGraphComponent implements OnInit, OnChanges {

    @Input() graphs: any;
    @Input() graph: any;
    @Input() labels: any;

    //  DATA TO COMPARE
    currentTypeGraph = 0;

    //  CHART.JS
    public lineChartOptions = {
        scaleShowVerticalLines: false,
        responsive: true,
        scales: {
            xAxes: [{
               
                scaleLabel: {
                    display: true,
                    labelString:  'Date'
                }
            }],
            yAxes: [{
                ticks: {
                    beginAtZero: true,
                    userCallback: function(val, index, values) {
                        val = val.toString();
                        val = val.split(/(?=(?:...)*$)/);
                        val = val.join(',');
                        return val;
                    }
                },
                scaleLabel: {
                    display: true,
                    labelString:  'Amount (x 1000)'
                }
            }]
        }
    };
    public lineChartLabels;
    public lineChartType = 'line';
    public lineChartLegend = true;
    public lineChartData = [];

    constructor() {
    }

    ngOnInit() {

        this.runLoadData();

    }

    ngOnChanges(){

        this.runLoadData();

    }

    

    runLoadData(){

        this.lineChartLabels = this.labels;

        //  EMPTY TO DISPLAY AGAIN
        this.lineChartData = [];

        const dataLoad = this.graph[this.currentTypeGraph];

        dataLoad.data.forEach((element, index) => {

            const shape = {
                data                : element,
                label               : dataLoad.name.split('&')[index],
                borderColor         : (index === 0) ? '#007bff' : '#ffc107',
                pointBackgroundColor: (index === 0) ? '#007bff' : '#ffc107',
                backgroundColor     : (index === 0) ? '#007bff' : '#ffc107',
                fill                : false,
            };
            this.lineChartData.push(shape);
        });
    }

    /**
     * Change the Type on the graph
     * @param index
     */
    changeTypeGraph(index){

        this.currentTypeGraph = index;

        this.runLoadData();

    }

    /*
    formatNumber(number, decimalsLength, decimalSeparator, thousandSeparator) {
        var n = number,
            decimalsLength = isNaN(decimalsLength = Math.abs(decimalsLength)) ? 2 : decimalsLength,
            decimalSeparator = decimalSeparator == undefined ? "," : decimalSeparator,
            thousandSeparator = thousandSeparator == undefined ? "." : thousandSeparator,
            sign = n < 0 ? "-" : "",
            i = parseInt(n = Math.abs(+n || 0).toFixed(decimalsLength)) + "",
            j = (j = i.length) > 3 ? j % 3 : 0;
 
        return sign +
            (j ? i.substr(0, j) + thousandSeparator : "") +
            i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousandSeparator) +
            (decimalsLength ? decimalSeparator + Math.abs(n - i).toFixed(decimalsLength).slice(2) : "");
    }  
    */

}