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
  private float colorIndex = 0;

  final BasicParameter speedParam = new BasicParameter("Speed", 5, 20, .01);
  final BasicParameter waveSlope = new BasicParameter("waveSlope", 360, 1, 720);
  final SawLFO wave = new SawLFO(0, 360, speedParam.getValuef() * speedMult);

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
      colorIndex = ((wave.getValuef() * 100) + cube.config.treeIndex) % 6;
      if (colorIndex == 0) {
        // Electric Red
        colors[cube.index] = lx.hsb( 0, 100, 91 );
      } else if (colorIndex == 1) {
        // Dark Orange
        colors[cube.index] = lx.hsb( 33, 100, 100 );
      } else if (colorIndex == 2) {
        // Canary Yellow
        colors[cube.index] = lx.hsb( 56, 100, 100 );
      } else if (colorIndex == 3) {
        // La Salle Green
        colors[cube.index] = lx.hsb( 134, 100, 51 );
      } else if (colorIndex == 4) {
        // Blue
        colors[cube.index] = lx.hsb( 224, 100, 100 );
      } else if (colorIndex == 5) {
        // Patriarch
        colors[cube.index] = lx.hsb( 292, 100, 54 );
      }
    }

    for (ShrubCube cube : model.shrubCubes) {
      colorIndex = ((wave.getValuef() * 100) + cube.config.shrubIndex) % 6;
      if (colorIndex == 0) {
        // Electric Red
        colors[cube.index] = lx.hsb( 0, 100, 91 );
      } else if (colorIndex == 1) {
        // Dark Orange
        colors[cube.index] = lx.hsb( 33, 100, 100 );
      } else if (colorIndex == 2) {
        // Canary Yellow
        colors[cube.index] = lx.hsb( 56, 100, 100 );
      } else if (colorIndex == 3) {
        // La Salle Green
        colors[cube.index] = lx.hsb( 134, 100, 51 );
      } else if (colorIndex == 4) {
        // Blue
        colors[cube.index] = lx.hsb( 224, 100, 100 );
      } else if (colorIndex == 5) {
        // Patriarch
        colors[cube.index] = lx.hsb( 292, 100, 54 );
      }
    }
  }
}
