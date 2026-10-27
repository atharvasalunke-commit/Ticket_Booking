#!/usr/bin/env bash

# Output file name
OUTPUT_FILE="all_java_code.txt"

# Empty the output file if it exists, or create it
> "$OUTPUT_FILE"

COUNT=0

# Loop through all .java files found under src/ in alphabetical order
while IFS= read -r file; do
    echo "================================================================================" >> "$OUTPUT_FILE"
    echo "FILE: $file" >> "$OUTPUT_FILE"
    echo "================================================================================" >> "$OUTPUT_FILE"
    echo "" >> "$OUTPUT_FILE"
    cat "$file" >> "$OUTPUT_FILE"
    echo "" >> "$OUTPUT_FILE"
    echo "" >> "$OUTPUT_FILE"
    COUNT=$((COUNT + 1))
done < <(find src -type f -name "*.java" | sort)

echo "Successfully combined $COUNT Java files into $OUTPUT_FILE"
