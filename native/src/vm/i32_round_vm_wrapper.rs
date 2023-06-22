use rufi_core::core::context::context::Context;
use rufi_core::core::export::export::Export;
use rufi_core::core::path::slot::slot::Slot;
use rufi_core::core::sensor_id::sensor_id::SensorId;
use rufi_core::core::vm::round_vm::round_vm::RoundVM;
use crate::vm::i32_round_vm_wrapper::i32_round_vm_wrapper::I32RoundVMWrapper;

pub mod i32_round_vm_wrapper {
    use rufi_core::core::vm::round_vm::round_vm::RoundVM;

    #[derive(Debug)]
    #[repr(transparent)]
    pub struct I32RoundVMWrapper {
        pub(crate) vm: RoundVM,
    }
}

impl I32RoundVMWrapper {
    #[no_mangle]
    pub extern "C" fn new(context: Context) -> Self {
        Self {
            vm: RoundVM::new_empty(context)
        }
    }

    #[no_mangle]
    pub extern "C" fn export_data(&mut self) -> &mut Export {
        self.vm.export_data()
    }

    #[no_mangle]
    pub extern "C" fn self_id(&self) -> i32 {
        self.vm.self_id()
    }

    #[no_mangle]
    pub extern "C" fn register_root(&mut self, v: i32) {
        self.vm.register_root(v)
    }

    #[no_mangle]
    pub extern "C" fn neighbor(&self) -> &Option<i32> {
        self.vm.neighbor()
    }

    #[no_mangle]
    pub extern "C" fn index(&self) -> &i32 {
        self.vm.index()
    }

    #[no_mangle]
    pub extern "C" fn previous_round_val(&self) -> Option<&i32> {
        self.vm.previous_round_val()
    }

    #[no_mangle]
    pub extern "C" fn neighbor_val(&self) -> Option<&i32> {
        self.vm.neighbor_val()
    }

    #[no_mangle]
    pub extern "C" fn local_sense(&self, sensor_id: &SensorId) -> Option<&i32> {
        self.vm.local_sense(sensor_id)
    }

    #[no_mangle]
    pub extern "C" fn nbr_sense(&self, sensor_id: &SensorId) -> Option<&i32> {
        self.vm.nbr_sense(sensor_id)
    }

    #[no_mangle]
    pub extern "C" fn folded_eval(&mut self, expr: extern fn () -> i32, id: i32) -> Option<i32> {
        self.vm.folded_eval(|| expr(), id)
    }

    #[no_mangle]
    pub extern "C" fn nest(&mut self, slot: Slot, write: bool, inc: bool, expr: extern fn () -> i32) -> i32 {
        self.vm.nest(slot, write, inc, || expr())
    }

    #[no_mangle]
    pub extern "C" fn locally(&mut self, expr: extern fn () -> i32) -> i32 {
        self.vm.locally(|| expr())
    }

    #[no_mangle]
    pub extern "C" fn aligned_neighbours(&self) -> Vec<i32> {
        self.vm.aligned_neighbours()
    }

    #[no_mangle]
    pub extern "C" fn isolate(&mut self, expr: extern fn () -> i32) -> i32 {
        self.vm.isolate(|| expr())
    }

    #[no_mangle]
    pub extern "C" fn unless_folding_on_others(&self) -> bool {
        self.vm.unless_folding_on_others()
    }

    #[no_mangle]
    pub extern "C" fn only_when_folding_on_self(&self) -> bool {
        self.vm.only_when_folding_on_self()
    }
}
