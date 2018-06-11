describe("cenzor", function () {
    jasmine.clock().install();

    beforeEach(function () {
        let s = spyOn(console, 'log').and.callThrough();
        $('body').append(`
        <div id="tocenzor">Do ocenzurowania</div>
        <div class="tcz">A</div>
        <div class="tcz">B</div>
        `);
    });

    afterEach(function () {
        $('#tocenzor').remove();
    });

    it("shold cenzor text", function () {
        $('#tocenzor').cenzor();
        expect($('#tocenzor').text()).toEqual('--censored--');
    });

    it("shold cenzor every text", function () {
        $('.tcz').cenzor();
        expect($('.tcz').first().text()).toEqual('--censored--');
        expect($('#tocenzor').text()).not.toBe('--censored--');
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