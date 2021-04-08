import {trigger, animate, style, group, animateChild, query, stagger, transition} from '@angular/animations';

export const routerTransition = trigger('routerTransition', [
    transition('* <=> *', [
        style({ position: 'relative' }),
        query(':enter, :leave', [
            style({
                position: 'absolute',
                top: 0,
                left: 0,
                width: '100%'
            })
        ], { optional: true }),
        query(':enter', [
            style({ left: '100%'})
        ], { optional: true }),
        query(':leave', animateChild(), { optional: true }),
        group([
            query(':leave', [
                animate('1000ms ease-out', style({ left: '100%'}))
            ], { optional: true }),
            query(':enter', [
                animate('1000ms ease-out', style({ left: '0%'}))
            ], { optional: true })
        ]),
        query(':enter', animateChild(), { optional: true }),
    ])
])