const {toolbox} = require("../js/toolbox.js"); // load module

describe("cenzor", function () {
    
    jasmine.clock().install();

    const tools = toolbox();

    beforeEach(function () {
        let s = spyOn(console, 'log').and.callThrough();
        let elem = document.createElement('div');
        elem.id = "mycontainer";
        elem.innerHTML = `
        <div class="tcz">ABC</div>
        <div class="tcz">BCD</div>
        `;
        document.body.appendChild(elem)
    });

    afterEach(function () {
        $('#mycontainer').remove();
    });

    it("returned value should be reversed", function () {
        expect(tools.reverse('dcba')).toEqual('abcd');
    });

    it("shold cenzor every text", function () {
        tools.reverse({selector: '.tcz'});
        
        expect(document.getElementsByClassName('tcz')[0].innerHTML).toEqual('CBA');
        expect(document.getElementsByClassName('tcz')[1].innerHTML).not.toBe('BCD');
        expect(console.log).not.toHaveBeenCalled();
    });


    it("shold handle clock properly", function () {
        let dt = new Date(2017,10,10);
        jasmine.clock().mockDate(dt);
        jasmine.clock().tick(50);
        expect(new Date().getTime()).toEqual(dt.getTime() + 50);
    });    

    it("shold call a spy", function () {
        console.log("Hi");
        expect(console.log).toHaveBeenCalled();
        expect(console.log).toHaveBeenCalledWith(jasmine.any(String));
    });
});

