import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-clientele-card',
  templateUrl: './clientele-card.component.html',
  styleUrls: ['./clientele-card.component.css']
})
export class ClienteleCardComponent implements OnInit {
  showMode: boolean = true ;
  specificCard: any;
  j: any;
  lifePd: any;
  provisions: any;
  newprov: any;
  diff : any ;

  @Input() card: any;
  constructor() { }
  
  ngOnInit() {
    if (this.card.title === 'Provisions') {
      this.lifePd = this.card.table.lifePd;
      this.provisions = this.card.table.provisions;
      this.newprov = this.card.table.newProvisions;
      this.diff = this.card.table.difference;
      this.card.table = this.card.table.values;
    };
  }

}
