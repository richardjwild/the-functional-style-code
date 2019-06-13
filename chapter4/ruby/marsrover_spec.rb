require 'rspec'
require './marsrover.rb'

describe 'MarsRoverBehavior' do

  before do
    @marsrover = Marsrover.new
  end

  after do
    # Do nothing
  end

  context 'when driven nowhere' do
    it 'remains in the initial position and bearing' do
      robot = @marsrover.simulate(0, 0, :north, '')
      expect(robot[:coords]).to(eq({:x => 0, :y => 0}))
      expect(robot[:bearing]).to(eq(:north))
    end
  end

  context 'when driven ahead three steps facing north' do
    it 'ends up three places north and facing north' do
      robot = @marsrover.simulate(0, 0, :north, 'AAA')
      expect(robot[:coords]).to(eq({:x => 0, :y => 3}))
      expect(robot[:bearing]).to(eq(:north))
    end
  end

  context 'when turned right then driven ahead one step' do
    it 'ends up one place east and facing east' do
      robot = @marsrover.simulate(0, 0, :north, 'RA')
      expect(robot[:coords]).to(eq({:x => 1, :y => 0}))
      expect(robot[:bearing]).to(eq(:east))
    end
  end

  context 'when turned left then driven ahead one step' do
    it 'ends up one place west and facing west' do
      robot = @marsrover.simulate(0, 0, :north, 'LA')
      expect(robot[:coords]).to(eq({:x => -1, :y => 0}))
      expect(robot[:bearing]).to(eq(:west))
    end
  end

  context 'when driven around in a circle' do
    it 'ends up where it started from' do
      robot = @marsrover.simulate(0, 0, :north, 'ARARARAR')
      expect(robot[:coords]).to(eq({:x => 0, :y => 0}))
      expect(robot[:bearing]).to(eq(:north))
    end
  end
end