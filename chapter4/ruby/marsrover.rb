class Marsrover

  def robot(coordinates, bearing)
    {
        :coords => coordinates,
        :bearing => bearing
    }
  end

  def coordinates(x, y)
    {
        :x => x, :y => y
    }
  end

  def rotations
    {
        :north => {
            :left => :west,
            :right => :east
        },
        :east => {
            :left => :north,
            :right => :south
        },
        :south => {
            :left => :east,
            :right => :west
        },
        :west => {
            :left => :south,
            :right => :north
        }
    }
  end

  def turn(robot, direction)
    bearing = robot[:bearing]
    rotation = rotations[bearing]
    robot(robot[:coords], rotation[direction])
  end

  def translations
    {
        :north => {:x => 0, :y => 1},
        :east => {:x => 1, :y => 0},
        :south => {:x => 0, :y => -1},
        :west => {:x => -1, :y => 0}
    }
  end

  def move(robot)
    bearing = robot[:bearing]
    coords = robot[:coords]
    delta = translations[bearing]
    new_coords = coordinates(coords[:x] + delta[:x], coords[:y] + delta[:y])
    robot(new_coords, bearing)
  end

  def do_step(robot, step)
    case step
    when 'L'
      return turn(robot, :left)
    when 'R'
      return turn(robot, :right)
    when 'A'
      return move(robot)
    else
      robot
    end
  end

  def simulate(x, y, bearing, steps)
    steps.chars.reduce(
        robot(coordinates(x, y), bearing),
        &method(:do_step))
  end

end