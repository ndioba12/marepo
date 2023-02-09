import {
    trigger,
    transition,
    style,
    query,
    group,
    animateChild,
    animate,
    keyframes
} from "@angular/animations";

// basic
export const fader = 
    trigger('routerAnimations',[
        transition('* <=> *',[
            query(':enter, :leave',[
                 style({
                    position:'absolute',
                    left:0,
                    top:0,
                    width:'100%',
                    opacity:0,
                    transform:'scale(0) translateX(100%)', 

                 })
            ]),
            query(':enter',[
                animate('600ms ease',style({
                    opacity:1,
                    transform:'scale(1) translateX(0)',
                }))
           ]) 
        ])

]);

// positionned
export const slider =
    trigger('routerAnimations',[
        transition('* => isLeft',slideTo('left')),
        transition('* => isRight',slideTo('right')),
        transition('isRight => *',slideTo('left')),
        transition('isLeft => *',slideTo('right'))
    ])

    function slideTo(direction:any){
        const optional = { optional :true };
        return[
            query(':enter, :leave',[
                style({
                   position:'absolute',
                   top:0,
                   [direction]:0,
                   width:'100%'
                })
           ],optional),
           query(':enter',[
            style({
                [direction]:'-100%',
            }),
            group([
                query(':leave',[
                    animate('600ms ease', style({[direction]:'100%'}) )
               ],optional), 
               query(':enter ',[
                animate('600ms ease', style({[direction]:'0%'}) )
           ],optional), 
            ])
       ])  
        ]; 
    }