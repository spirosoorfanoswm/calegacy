// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

/*export const environment = {
    production: false,
    clienteleLengthLoad: 100,
    vatLabel:'QID/CRN',
    //  API SERVICE URL
    url: '',
    configinfo: '/repository/configinfo',
    contextinfo: '/repository/contextinfo',
    clientdistribution: '/repository/clienteledistribution',
    clientelestatistics: '/repository/clientelestatistics',
    clientelestatisticsgraph: '/repository/clientelestatisticsgraph',
    customers: '/repository/customers',
    customersDev: '/customerDev',
    searchcustomers: '/repository/customers/search',
    scenarios: '/scenario/fetch',
    scenarios_put_post_delete: '/scenario',
    scenario_template: '/scenario/template',
    customer:'/customer'
};*/

export const environment = {
    production: false,
    clienteleLengthLoad: 100,
    vatLabel:'VAT number',
    url: 'http://localhost:3001',
    configinfo: '/configinfo',
    contextinfo: '/contextinfo',
    clientdistribution: '/clienteledistribution',
    clientelestatistics: '/clientelestatistics',
    clientelestatisticsgraph: '/clientelestatisticsgraph',
    customers: '/customers',
    exportcustomers: '/exportcustomers',
    exportcustomer: '/exportcustome',
    customersDev: '/customerDev',
    searchcustomers: '/customerSearch',
    scenarios: '/scenarios',
    scenarios_put_post_delete: '/scenarios',
    scenario_template: '/template',
    customer:'/customer',
    //modelvalidation:'/modelValidationRules',
    download:'/export/downloadfile',
    exportclienteledistribution:'/export/clienteledistribution',
    exportstatistics:'/export/statistics',
    downloadreportsfile:'/export/downloadreportsfile'
};

/*export const environment = {
    production: false,
    clienteleLengthLoad: 100,
    //  API SERVICE URL
    url: '',
    configinfo: '/repository/configinfo',
    contextinfo: '/repository/contextinfo',
    clientdistribution: '/repository/clienteledistribution',
    clientelestatistics: '/repository/clientelestatistics',
    clientelestatisticsgraph: '/repository/clientelestatisticsgraph',
    customers: '/repository/customers',
    customersDev: '/customerDev',
    searchcustomers: '/repository/customers/search',
    scenarios: '/scenario/fetch',
    scenarios_put_post_delete: '/scenario',
    scenario_template: '/scenario/template'
};*/
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
