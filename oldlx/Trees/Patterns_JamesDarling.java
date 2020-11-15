import heronarts.lx.LX;
import heronarts.lx.modulator.SawLFO;
import heronarts.lx.parameter.BasicParameter;

/**
James' mangling on ColorWave to experiment
*/
class JamesTest extends TSPattern {

  // Variable Declarations go here
  private float minx = Float.MAX_VALUE;
  private float maxx = -Float.MAX_VALUE;
  private float waveWidth = 1;
  private float speedMult = 1000;

  final BasicParameter speedParam = new BasicParameter("Speed", 5, 20, .01);
  final BasicParameter waveSlope = new BasicParameter("waveSlope", 360, 1, 720);
  final SawLFO wave = new SawLFO(0, 360, speedParam.getValuef() * speedMult);

  // add speed, wave width

  // Constructor and inital setup
  // Remember to use addParameter and addModulator if you're using Parameters or sin waves
  JamesTest(LX lx) {
    super(lx);
    addModulator(wave).start();
    addParameter(waveSlope);
    addParameter(speedParam);

    for (Cube cube : model.cubes) {
      if (cube.x < minx) {minx = cube.x;}
      if (cube.x > maxx) {maxx = cube.x;}
    }
  }


  // This is the pattern loop, which will run continuously via LX
  public void run(double deltaMs) {
    wave.setPeriod(speedParam.getValuef() * speedMult);

      // Use a for loop here to set the cube colors
      for (Cube cube : model.cubes) {
        colors[cube.index] = lx.hsb( (float)( (wave.getValuef() + waveSlope.getValuef() * Utils.map(cube.x, minx, maxx) ) % 360), 100, 100);
      }


      for (ShrubCube cube : model.shrubCubes) {
        colors[cube.index] = lx.hsb( (float)( (wave.getValuef() + waveSlope.getValuef() * Utils.map(cube.x, minx, maxx) ) % 360), 100, 100);
      }
  }
}
