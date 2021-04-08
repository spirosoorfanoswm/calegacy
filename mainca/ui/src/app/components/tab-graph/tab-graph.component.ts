import {Component, Input, OnChanges, OnInit} from '@angular/core';

@Component({
    selector: 'app-tab-graph',
    templateUrl: './tab-graph.component.html',
    styleUrls: ['./tab-graph.component.css']
})
export class TabGraphComponent implements OnInit, OnChanges {

    @Input() dataTabs: any;
    @Input() dataGraph: any;
    @Input() dataStats: any;
    @Input() typeChart: string;
    dataLoad: any;

    currentTypeGraph = 0;

    //  CHART.JS
    public ChartOptions = {
        scaleShowVerticalLines: false,
        responsive: true,
        scales: {
            xAxes: [{
                //stacked: true,
                gridLines: {
                    color: '#fff'
                },
                ticks: {
                    beginAtZero: true,
                    userCallback: function(value, index, values) {
                        value = value.toString();
                        value = value.split(/(?=(?:...)*$)/);
                        value = value.join(',');
                        return value;
                    }
                },
                scaleLabel: {
                    display: true,
                    labelString:  'Amount'
                }
            }],
            yAxes: [{
                //stacked: true,
                gridLines: {
                    color: '#fff'
                },  scaleLabel: {
                    display: true,
                    labelString: 'CAS'
                }
            }]
        }
    };
    public ChartLabels: any;
    public ChartType: string;
    public ChartLegend = true;
    public ChartData = [];

    constructor() {
    }

    ngOnInit() {

        //  RESET GRAPH
        this.resetGraph();
        //  LOAD GRAPH
        this.runLoadData();

    }

    ngOnChanges() {

        //  RESET GRAPH
        this.resetGraph();
        //  LOAD GRAPH
        this.runLoadData();
    }

    /**
     * RESET GRAPH
     */
    resetGraph() {

        this.ChartLabels = [];
        this.ChartData = [];

    }

    /**
     * LOAD DATA TO GRAPHS
     */
    runLoadData() {

        var _this = this;

        var labelsGraph = [];

        var uniqueKeys = [];

        var graphValue = {};

        this.ChartType = this.typeChart;

        //  Get Labels
        this.dataGraph[this.currentTypeGraph].forEach(value => {

            uniqueKeys.push( value.description );

            value.values.forEach(label => {

                if (!labelsGraph.includes(label.key)) {

                    labelsGraph.push(label.key);

                    graphValue[label.key] = [];
                    graphValue[label.key].push(
                        label.value
                    );
                }else{
                    graphValue[label.key].push(
                        label.value
                    );
                }
            });
        });

        this.ChartLabels = uniqueKeys;

        Object.keys(graphValue).forEach(function(key,val) {

            const shape = {
                data                : graphValue[key],
                label               : key,
                backgroundColor     : (val == 0) ? '#3C94DE' : '#C3DFF5',
                hoverBackgroundColor: (val == 0) ? '#3C94DE' : '#C3DFF5',
            };

            _this.ChartData.push(shape);
        });
    }

    /**
     * Change the Type on the graph
     * @param index
     */
    changeTypeGraph(index) {

        this.currentTypeGraph = index;

        this.resetGraph();

        this.runLoadData();

    }

}
