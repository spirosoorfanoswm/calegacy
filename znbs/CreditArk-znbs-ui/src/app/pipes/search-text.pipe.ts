import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'searchText'
})
export class SearchTextPipe implements PipeTransform {

    transform(items: any[], params: any): any[] {

        if (!items) {
            return  null;
        }

        if (!params) {
            return items;
        }

        let paramsText = params.name.toLowerCase();

        let paramsInt =  parseInt(params.name, 10);


        return items.filter(item => {
            return JSON.stringify(item[params.key]).toLowerCase().includes(paramsText);
        });
    }
}
