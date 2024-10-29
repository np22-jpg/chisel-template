package led

import chisel3._
import chisel3.util.Counter
import circt.stage.ChiselStage

// class Blinky(freq: Int, startOn: Boolean = false) extends Module {
//   val io = IO(new Bundle {
//     val led0 = Output(Bool())
//   })
//   // Blink LED every second using Chisel built-in util.Counter
//   val led = RegInit(startOn.B)
//   val (_, counterWrap) = Counter(true.B, freq / 2)
//   when(counterWrap) {
//     led := ~led
//   }
//   io.led0 := led
// }
class Hello extends Module {
  val io = IO(new Bundle {
    val led = Output(UInt(1.W))
  })
  val CNT_MAX = (50000000 / 2 - 1).U
  val cntReg = RegInit(0.U(32.W))
  val blkReg = RegInit(0.U(1.W))
  cntReg := cntReg + 1.U
  when(cntReg === CNT_MAX) {
    cntReg := 0.U
    blkReg := ~blkReg
  }
  io.led := blkReg
}

object Main extends App {
  // These lines generate the Verilog output
  ChiselStage.emitSystemVerilogFile(
    new Hello,
    firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info")
  )
}

// // Generate Verilog sources and save it in file led.v
// object Main extends App {
//   ChiselStage.emitSystemVerilogFile(
//     new Blinky,
//     firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info")
//   )
// }
